package fast.campus.netplix3.tmdb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fast.campus.netplix3.client.TmdbHttpClient;
import fast.campus.netplix3.movie.TmdbMoviePort;
import fast.campus.netplix3.movie.TmdbPagableMovies;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TmdbMovieListHttpClient implements TmdbMoviePort {

    @Value("${tmdb.api.movie-lists.now-playing}")
    private String nowPlaying;

    private final TmdbHttpClient tmdbHttpClient;

    @Override
    public TmdbPagableMovies fetchPageable(int page) {
        String url = nowPlaying + "?language=ko-KR&page=" + page;
        String request = tmdbHttpClient.request(url, HttpMethod.GET, CollectionUtils.toMultiValueMap(Map.of()), Map.of());

        TmdbResponse response;
        try {
            response =  new ObjectMapper().readValue(request, TmdbResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new TmdbPagableMovies(
                response.getResults().stream()
                        .map(TmdbMovieNowPlaying::toDomain)
                        .toList(),
                Integer.parseInt(response.getPage()),
                (Integer.parseInt(response.getTotal_pages())) - page != 0
        );
    }
}
