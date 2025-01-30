package fast.campus.netplix3.movie.response;


import lombok.Getter;


import java.util.List;

@Getter
public class PageableMoviesResponse {
    private final List<MovieResponse> movieResponses;

    private final int page;
    private final Boolean hasNext;

    public PageableMoviesResponse(List<MovieResponse> movieResponses, int page, Boolean hasNext) {

        this.movieResponses = movieResponses;
        this.page = page;
        this.hasNext = hasNext;
    }
}
