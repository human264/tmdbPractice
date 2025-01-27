package fast.campus.netplix3.movie;

import fast.campus.netplix3.movie.response.PageableMoviesResponse;

public interface FetchMovieUseCase {
    PageableMoviesResponse fetchFromClient(int page);
}
