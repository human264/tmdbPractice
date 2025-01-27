package fast.campus.netplix3.movie;

public interface TmdbMoviePort {
    TmdbPagableMovies fetchPageable(int page);
}
