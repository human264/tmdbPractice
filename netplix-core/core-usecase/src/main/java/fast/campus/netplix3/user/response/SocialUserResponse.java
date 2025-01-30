package fast.campus.netplix3.user.response;

public record SocialUserResponse(
        String name,
        String provider,
        String providerId
) {
}
