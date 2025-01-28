package fast.campus.netplix3.controller.user;


import fast.campus.netplix3.controller.NetplixApiResponse;
import fast.campus.netplix3.controller.user.request.UserRegisterRequest;
import fast.campus.netplix3.user.RegisterUserUseCase;
import fast.campus.netplix3.user.command.UserRegistrationCommand;
import fast.campus.netplix3.user.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

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
}
