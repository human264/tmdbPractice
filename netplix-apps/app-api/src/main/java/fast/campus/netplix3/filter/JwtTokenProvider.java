package fast.campus.netplix3.filter;

import fast.campus.netplix3.token.FetchTokenUseCase;
import fast.campus.netplix3.user.command.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final FetchTokenUseCase fetchTokenUseCase;

    public Authentication getAuthentication(String accessToken) {
        UserResponse user = fetchTokenUseCase.findUserByAccessToken(accessToken);
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("abcd"));
        UserDetails principal = new User(
                user.getUsername(),
                StringUtils.isBlank(user.getPassword()) ? "password" : user.getPassword(),
                authorities
        );
        return new UsernamePasswordAuthenticationToken(principal, user.getUserId(), authorities);
    };

}
