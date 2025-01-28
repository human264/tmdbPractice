package fast.campus.netplix3.sample;

import fast.campus.netplix3.sample.response.SampleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService implements SearchSampleUseCase {

    private final SamplePort samplePort;
    private final SamplePersistencePort samplePersistencePort;

    @Override
    public SampleResponse getSample() {
        SamplePortResponse sample = samplePort.getSample();
        String sampleName = samplePersistencePort.getSampleName("1");
        return new SampleResponse(sample.getName());
    }


}
