package fast.campus.netplix3.repository;


import fast.campus.netplix3.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleJpaRepository extends JpaRepository<SampleEntity, Long>, SampleCustomRepository {
    Optional<SampleEntity> findById(String id);
}
