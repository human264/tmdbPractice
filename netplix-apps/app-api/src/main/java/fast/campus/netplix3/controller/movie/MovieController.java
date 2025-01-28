package fast.campus.netplix3.controller.movie;


import fast.campus.netplix3.movie.FetchMovieUseCase;
import fast.campus.netplix3.movie.response.PageableMoviesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final FetchMovieUseCase fetchMovieUseCase;

    @GetMapping("/api/v1/movie/client/{page}")
    public String fetchMoviePageable(@PathVariable("page") int page) {
        PageableMoviesResponse response = fetchMovieUseCase.fetchFromClient(page);
        return "";
    }
}
