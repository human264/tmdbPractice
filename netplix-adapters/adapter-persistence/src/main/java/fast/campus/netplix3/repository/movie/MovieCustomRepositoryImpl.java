package fast.campus.netplix3.repository.movie;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix3.entity.movie.MovieEntity;
import fast.campus.netplix3.entity.movie.QMovieEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MovieEntity> findByMovieName(String name) {
        return jpaQueryFactory.selectFrom(QMovieEntity.movieEntity)
                .where(QMovieEntity.movieEntity.movieName.eq(name))
                .fetch()
                .stream()
                .findFirst();
    }


    @Override
    public Page<MovieEntity> search(Pageable pageable) {
        List<MovieEntity> fetch = jpaQueryFactory.selectFrom(QMovieEntity.movieEntity)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = jpaQueryFactory.selectFrom(QMovieEntity.movieEntity)
                .fetch().size();

        return new PageImpl<>(fetch, pageable, count);
    }
}
