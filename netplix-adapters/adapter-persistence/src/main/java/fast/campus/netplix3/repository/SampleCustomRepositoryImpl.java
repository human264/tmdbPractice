package fast.campus.netplix3.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix3.entity.QSampleEntity;
import fast.campus.netplix3.entity.SampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SampleCustomRepositoryImpl implements SampleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SampleEntity> findAllABC() {
        return jpaQueryFactory.selectFrom(QSampleEntity.sampleEntity).fetch();
    }
}
