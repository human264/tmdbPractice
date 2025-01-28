package fast.campus.netplix3.intercepter;

import fast.campus.netplix3.authentication.AuthenticationHolder;
import fast.campus.netplix3.authentication.RequestBy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
@RequiredArgsConstructor
public class RequstedByInterceptor implements WebRequestInterceptor {

    public static final String REQUESTED_BY_HEADER = "requested_by";

    private final AuthenticationHolder authenticationHolder;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        String requestedBy = request.getHeader(REQUESTED_BY_HEADER);
        RequestBy requested = new RequestBy(requestedBy);
        authenticationHolder.setAuthentication(requested);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}
