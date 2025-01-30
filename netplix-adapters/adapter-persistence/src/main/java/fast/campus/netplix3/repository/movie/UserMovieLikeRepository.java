package fast.campus.netplix3.repository.movie;

import fast.campus.netplix3.entity.movie.UserMovieLikeEntity;
import fast.campus.netplix3.movie.LikeMoviePort;
import fast.campus.netplix3.movie.UserMovieLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserMovieLikeRepository implements LikeMoviePort {

    private final UserMovieLikeJpaRepository userMovieLikeJpaRepository;

    @Override
    @Transactional
    public UserMovieLike save(UserMovieLike domain) {
        UserMovieLikeEntity entity = UserMovieLikeEntity.toEntity(domain);
        return userMovieLikeJpaRepository.save(entity).toDomain();
    }

    @Override
    @Transactional
    public Optional<UserMovieLike> findByUserIdAndMovieId(String userId, String movieId) {
        return userMovieLikeJpaRepository.findByUserIdAndMovieId(userId, movieId)
                .map(UserMovieLikeEntity::toDomain);
    }
}
