package fast.campus.netplix3.repository;

import fast.campus.netplix3.entity.SampleEntity;
import fast.campus.netplix3.sample.SamplePersistencePort;
import fast.campus.netplix3.sample.SamplePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SampleRepository implements SamplePersistencePort {

    private final SampleJpaRepository sampleJpaRepository;

    @Transactional
    @Override
    public String getSampleName(String id) {
        Optional<SampleEntity> byId = sampleJpaRepository.findById(id);
        return byId.map(SampleEntity::getSampleId).orElseThrow();
    }
}
