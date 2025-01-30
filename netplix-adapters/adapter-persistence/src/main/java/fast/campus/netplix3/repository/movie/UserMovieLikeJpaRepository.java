package fast.campus.netplix3.repository.movie;


import fast.campus.netplix3.entity.movie.UserMovieLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMovieLikeJpaRepository extends JpaRepository<UserMovieLikeEntity, String> {
    Optional<UserMovieLikeEntity> findByUserIdAndMovieId(String userId, String movieId);
}
