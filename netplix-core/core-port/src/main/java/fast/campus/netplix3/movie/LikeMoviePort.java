package fast.campus.netplix3.movie;

import java.util.Optional;

public interface LikeMoviePort {
    UserMovieLike save(UserMovieLike domain);

    Optional<UserMovieLike> findByUserIdAndMovieId(String userId, String movieId);
}
