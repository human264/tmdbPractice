package fast.campus.netplix3.repository.movie;

import fast.campus.netplix3.entity.movie.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieJpaRepository extends JpaRepository<MovieEntity, String>, MovieCustomRepository {


}
