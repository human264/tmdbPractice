package fast.campus.netplix3.controller.movie;


import fast.campus.netplix3.controller.NetplixApiResponse;
import fast.campus.netplix3.movie.FetchMovieUseCase;
import fast.campus.netplix3.movie.response.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final FetchMovieUseCase fetchMovieUseCase;

    @GetMapping("/api/v1/movie/client/{page}")
    public NetplixApiResponse<?> fetchMoviePageable(@PathVariable("page") int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
        return NetplixApiResponse.ok(pageableMoviesResponse);
    }

    @PostMapping("/api/v1/movie/search")
    public NetplixApiResponse<PageableMoviesResponse> search(@RequestParam("page") int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromDb(page);
        return NetplixApiResponse.ok(pageableMoviesResponse);

    }
}
