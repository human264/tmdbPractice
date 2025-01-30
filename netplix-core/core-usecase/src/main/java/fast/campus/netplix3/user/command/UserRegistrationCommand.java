package fast.campus.netplix3.user.command;

import lombok.Builder;

@Builder
public record UserRegistrationCommand(String username, String encryptedPassword, String email, String phone) {
}
