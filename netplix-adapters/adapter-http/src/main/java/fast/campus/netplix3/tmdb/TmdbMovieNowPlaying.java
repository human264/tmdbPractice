package fast.campus.netplix3.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import fast.campus.netplix3.movie.TmdbMovie;
import fast.campus.netplix3.movie.TmdbPagableMovies;
import lombok.Getter;

import java.util.List;

@Getter
public class TmdbMovieNowPlaying {
    private Boolean adult;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("genre_ids")
    private List<String> genreIds;

    private Integer id;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("original_title")
    private String originalTitle;

    private String overview;

    private String popularity;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("release_date")
    private String releaseDate;

    private String title;

    private String video;

    @JsonProperty("vote_average")
    private String voteAverage;

    @JsonProperty("vote_count")
    private String voteCount;

    public TmdbMovie toDomain() {
        return TmdbMovie.builder()
                .movieName(title)
                .isAdult(adult)
                .genre(TmdbMovieGenre.getGenreNamesByIds(genreIds))
                .overview(overview)
                .releasedAt(releaseDate)
                .build();
    }
}
