package fast.campus.netplix3.movie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TmdbPagableMovies {

    private final List<TmdbMovie> tmdbMovies;
    private final int page;
    private final boolean hasNext;
}
