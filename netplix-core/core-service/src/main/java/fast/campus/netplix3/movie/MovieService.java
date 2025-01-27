package fast.campus.netplix3.movie;

import fast.campus.netplix3.movie.response.MovieResponse;
import fast.campus.netplix3.movie.response.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase {

    private final TmdbMoviePort tmdbMoviePort;

    @Override
    public PageableMoviesResponse fetchFromClient(int page) {
        TmdbPagableMovies tmdbPagableMovies = tmdbMoviePort.fetchPageable(page);
        return new PageableMoviesResponse(
                tmdbPagableMovies.getTmdbMovies().stream().map(
                        movie -> new MovieResponse(
                                movie.getMovieName(),
                                movie.getIsAdult(),
                                movie.getGenre(),
                                movie.getOverview(),
                                movie.getReleasedAt()
                                )).toList(),
                tmdbPagableMovies.getPage(),
                tmdbPagableMovies.isHasNext()
        );
    }
}
