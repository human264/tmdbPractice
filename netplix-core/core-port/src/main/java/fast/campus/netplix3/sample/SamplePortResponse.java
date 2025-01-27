package fast.campus.netplix3.sample;

import lombok.Getter;

@Getter
public class SamplePortResponse {

    private final String name;

    public SamplePortResponse(String name) {
        this.name = name;
    }

}
