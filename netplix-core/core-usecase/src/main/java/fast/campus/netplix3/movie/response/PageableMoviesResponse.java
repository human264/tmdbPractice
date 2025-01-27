package fast.campus.netplix3.movie.response;


import lombok.Getter;


import java.util.List;

@Getter
public class PageableMoviesResponse {
    private final List<MovieResponse> movies;

    private final int page;
    private final Boolean hasNext;

    public PageableMoviesResponse(List<MovieResponse> movies, int page, Boolean hasNext) {
        this.movies = movies;
        this.page = page;
        this.hasNext = hasNext;
    }
}
