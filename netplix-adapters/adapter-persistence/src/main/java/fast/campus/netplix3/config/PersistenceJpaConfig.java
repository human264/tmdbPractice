package fast.campus.netplix3.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix3.entity.EntityModule;
import fast.campus.netplix3.repository.RepositoryModule;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = EntityModule.class)
@EnableJpaRepositories(basePackageClasses = RepositoryModule.class)
public class PersistenceJpaConfig {

    @PersistenceContext
    public EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
