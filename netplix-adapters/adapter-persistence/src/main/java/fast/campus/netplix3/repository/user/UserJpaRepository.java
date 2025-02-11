package fast.campus.netplix3.repository.user;

import fast.campus.netplix3.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

}
