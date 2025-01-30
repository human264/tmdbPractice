package fast.campus.netplix3.repository.movie;


import fast.campus.netplix3.entity.movie.UserMovieDownloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMovieDownloadJpaRepository extends JpaRepository<UserMovieDownloadEntity, String>, UserMovieDownloadCustomRepository {

}
