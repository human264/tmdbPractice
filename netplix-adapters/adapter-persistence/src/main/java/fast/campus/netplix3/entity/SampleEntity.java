package fast.campus.netplix3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "sample")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleEntity {
    @Id
    @Column(name = "SAMPLE_ID")
    private String sampleId;

    @Column(name = "SAMPLE_NMAE")
    private String sampleName;

    @Column(name = "SAMPLE_DESC")
    private String sampleDescription;


}
