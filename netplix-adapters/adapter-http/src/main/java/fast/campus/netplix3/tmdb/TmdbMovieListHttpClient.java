package fast.campus.netplix3.tmdb;

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
    private String nowPlayingUrl;

    private final TmdbHttpClient tmdbHttpClient;

    @Override
    public TmdbPagableMovies fetchPageable(int page) {
        String url = nowPlayingUrl + "?language=ko-KR&page" + page;
        String request = tmdbHttpClient.request(url, HttpMethod.GET, CollectionUtils.toMultiValueMap(Map.of()), Map.of());

//        TmdbResponse object = ObjectMapperUtil.toObject(request, TmdbResponse.class);
        return null;
    }
}
