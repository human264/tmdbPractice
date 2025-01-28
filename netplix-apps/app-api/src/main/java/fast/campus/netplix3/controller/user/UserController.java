package fast.campus.netplix3.controller.user;


import fast.campus.netplix3.controller.NetplixApiResponse;
import fast.campus.netplix3.controller.user.request.UserLoginRequest;
import fast.campus.netplix3.controller.user.request.UserRegisterRequest;
import fast.campus.netplix3.security.NetplixAuthUser;
import fast.campus.netplix3.user.RegisterUserUseCase;
import fast.campus.netplix3.user.command.UserRegistrationCommand;
import fast.campus.netplix3.user.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/api/v1/user/register")
    public NetplixApiResponse<?> register(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse register = registerUserUseCase.register(
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

        return NetplixApiResponse.ok("access-token");
    }
}
