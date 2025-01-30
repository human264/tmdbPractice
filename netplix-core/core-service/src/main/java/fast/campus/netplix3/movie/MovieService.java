package fast.campus.netplix3.movie;

import fast.campus.netplix3.movie.response.MovieResponse;
import fast.campus.netplix3.movie.response.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase {

    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;

    @Override
    public PageableMoviesResponse fetchFromClient(int page) {
        TmdbPagableMovies tmdbPagableMovies = tmdbMoviePort.fetchPageable(page);
        return new PageableMoviesResponse(
                tmdbPagableMovies.getTmdbMovies().stream().map(
                        movie -> new MovieResponse(
                                movie.getMovieName(),
                                movie.getIsAdult(),
                                Collections.singletonList(movie.getGenre()),
                                movie.getOverview(),
                                movie.getReleasedAt()
                        )).toList(),
                tmdbPagableMovies.getPage(),
                tmdbPagableMovies.isHasNext()
        );
    }

    @Override
    public PageableMoviesResponse fetchFromDb(int page) {
        List<NetplixMovie> netplixMovies = persistenceMoviePort.fetchBy(page, 10);
        return new PageableMoviesResponse(
                netplixMovies.stream().map(it -> new MovieResponse(
                        it.getMovieName(), it.getIsAdult(), List.of(), it.getOverview(), it.getReleasedAt())).toList(),
                page,
                true
        );
    }

    @Override
    public void insert(List<MovieResponse> items) {
        items.forEach(it -> {
                    NetplixMovie netplixMovie = NetplixMovie.builder()
                            .movieName(it.getMovieName())
                            .isAdult(it.getIsAdult())
                            .overview(it.getOverview())
                            .releasedAt(it.getReleasedAt())
                            .genre(it.getGenre().get(0))
                            .build();
            persistenceMoviePort.insert(netplixMovie);
                }
        );
    }
}
