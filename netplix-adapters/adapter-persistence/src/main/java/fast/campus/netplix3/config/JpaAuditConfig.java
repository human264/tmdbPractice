package fast.campus.netplix3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(
        auditorAwareRef = "requestedByAuditorAware",
        dateTimeProviderRef = "requestedAtAuditorAware"
)
@Configuration
public class JpaAuditConfig {
    
}
