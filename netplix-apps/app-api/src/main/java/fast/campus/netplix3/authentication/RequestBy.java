package fast.campus.netplix3.authentication;

import lombok.Getter;

@Getter
public class RequestBy implements Authentication {

    private final String requestedBy;

    public RequestBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

}
