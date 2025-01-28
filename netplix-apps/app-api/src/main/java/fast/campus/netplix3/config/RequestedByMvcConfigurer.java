package fast.campus.netplix3.config;

import fast.campus.netplix3.intercepter.RequestedByInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@EnableWebMvc
@RequiredArgsConstructor
public class RequestedByMvcConfigurer implements WebMvcConfigurer {

    private final RequestedByInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(interceptor);
    }
}
