package fast.campus.netplix3.repository.movie;

import fast.campus.netplix3.entity.movie.MovieEntity;
import fast.campus.netplix3.movie.NetplixMovie;
import fast.campus.netplix3.movie.PersistenceMoviePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRepository implements PersistenceMoviePort {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    @Transactional
    public List<NetplixMovie> fetchBy(int page, int size) {
        return movieJpaRepository.search(PageRequest.of(page, size))
                .stream().map(MovieEntity::toDomain)
                .toList();
    }

    @Transactional
    @Override
    public NetplixMovie findBy(String movieName) {
        return movieJpaRepository.findByMovieName(movieName)
                .map(MovieEntity::toDomain)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void insert(NetplixMovie netplixMovie) {

        Optional<MovieEntity> byMovieName = movieJpaRepository.findByMovieName(netplixMovie.getMovieName());

        if (byMovieName.isPresent()) {
            return;
        }
        MovieEntity movieEntity = MovieEntity.newEntity(
                netplixMovie.getMovieName(),
                netplixMovie.getIsAdult(),
                netplixMovie.getGenre(),
                netplixMovie.getOverview(),
                netplixMovie.getReleasedAt()
        );
        movieJpaRepository.save(movieEntity);
    }
}
