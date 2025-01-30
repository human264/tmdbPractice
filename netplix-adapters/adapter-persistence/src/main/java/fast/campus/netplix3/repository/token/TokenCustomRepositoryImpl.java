package fast.campus.netplix3.repository.token;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix3.entity.token.QTokenEntity;
import fast.campus.netplix3.entity.token.TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<TokenEntity> findByToken(String userId) {
        return jpaQueryFactory.selectFrom(QTokenEntity.tokenEntity)
                .where(QTokenEntity.tokenEntity.userId.eq(userId))
                .fetch()
                .stream().findFirst();
    }
}
