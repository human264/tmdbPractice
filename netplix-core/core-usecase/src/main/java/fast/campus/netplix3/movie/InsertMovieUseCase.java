package fast.campus.netplix3.movie;

import fast.campus.netplix3.movie.response.MovieResponse;

import java.util.List;

public interface InsertMovieUseCase {
    void insert(List<MovieResponse> items);
}
