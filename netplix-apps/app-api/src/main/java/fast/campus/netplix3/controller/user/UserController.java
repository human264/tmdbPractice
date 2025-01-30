package fast.campus.netplix3.controller.user;


import fast.campus.netplix3.controller.NetplixApiResponse;
import fast.campus.netplix3.controller.user.request.UserLoginRequest;
import fast.campus.netplix3.controller.user.request.UserRegisterRequest;
import fast.campus.netplix3.security.NetplixAuthUser;
import fast.campus.netplix3.token.FetchTokenUseCase;
import fast.campus.netplix3.token.UpdateTokenUseCase;
import fast.campus.netplix3.user.FetchUserUseCase;
import fast.campus.netplix3.user.RegisterUserUseCase;
import fast.campus.netplix3.user.command.UserRegistrationCommand;
import fast.campus.netplix3.user.command.UserResponse;
import fast.campus.netplix3.user.response.SocialUserResponse;
import fast.campus.netplix3.user.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final FetchTokenUseCase fetchTokenUseCase;
    private final FetchUserUseCase fetchUserUseCase;
    private final UpdateTokenUseCase updateTokenUseCase;

    @PostMapping("/api/v1/user/register")
    public NetplixApiResponse<?> register(@RequestBody UserRegisterRequest request) {
        UserRegistrationResponse register = registerUserUseCase.register(
                UserRegistrationCommand.builder()
                        .email(request.getEmail())
                        .username(request.getUsername())
                        .encryptedPassword(request.getPassword())
                        .phone(request.getEmail())
                        .build());
        return NetplixApiResponse.ok(register);
    }

    @PostMapping("/api/v1/user/login")
    public NetplixApiResponse<String> login(@RequestBody UserLoginRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);

        NetplixAuthUser principal = (NetplixAuthUser) authenticate.getPrincipal();

        String tokenResponse = updateTokenUseCase.upsertToken(principal.getEmail());

        return NetplixApiResponse.ok(tokenResponse);
    }

    @PostMapping("/api/v1/user/callback")
    public NetplixApiResponse<String> kakaoCallback(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        String tokenFromKakao = fetchTokenUseCase.getTokenFromKakao(code);
        SocialUserResponse kakaoUser = fetchUserUseCase.findKakaoUser(tokenFromKakao);

        UserResponse byProviderId = fetchUserUseCase.findByProviderId(kakaoUser.providerId());
        if (ObjectUtils.isEmpty(byProviderId)) {
            registerUserUseCase.registerSocialUser(
                    kakaoUser.name(),
                    kakaoUser.provider(),
                    kakaoUser.providerId()
            );
        }
        return NetplixApiResponse.ok(updateTokenUseCase.upsertToken(kakaoUser.providerId()));
    }
}
