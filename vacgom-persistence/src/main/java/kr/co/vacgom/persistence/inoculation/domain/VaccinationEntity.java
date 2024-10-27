package kr.co.vacgom.persistence.inoculation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.vacgom.persistence.disease.domain.DiseaseEntity;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.global.util.UuidBinaryConverter;
import kr.co.vacgom.persistence.global.util.UuidUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "TB_VACCINATION")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class VaccinationEntity extends BaseEntity {

    @Id
    @Convert(converter = UuidBinaryConverter.class)
    @Column(name = "VACCINATION_ID", columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    @Comment("[Not Null] 백신 접종 내역 엔티티 Id")
    private UUID id = UuidUtility.generateRandomUUID();

    @Column(nullable = true)
    @Comment("[Nullable] 백신 접종 차수")
    private Long doseRound;

    @Column
    @Comment("[Nullable] 백신 접종 차수 및 접종 정보")
    private String doseDescription;

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Comment("[Not Null] 백신 접종 일자")
    private LocalDate vaccinationDate;

    @Column(nullable = false)
    @Comment("[Nullable] 백신 접종 기관")
    private String vaccinationFacility;

    @Comment("[Nullable] 백신 제조사")
    private String vaccinationManufacturer;

    @Comment("[Nullable] 판매 허가된 백신 정규 명칭")
    private String vaccineProductName;

    @Comment("[Nullable] 백신 고유 로트번호")
    private String vaccineLotNumber;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "DISEASE_ID")
    @Comment("[NotNull] 질병(Disease) Id")
    private DiseaseEntity disease;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "VACCINE_ID")
    @Comment("[NotNull] 백신(Vaccine) Id")
    private VaccineEntity vaccine;

    public static VaccinationEntity create(
            Long doseRound,
            String doseDescription,
            LocalDate vaccinationDate,
            String vaccinationFacility,
            String vaccinationManufacturer,
            String vaccineProductName,
            String vaccineLotNumber,
            DiseaseEntity disease,
            VaccineEntity vaccine
    ) {
        return VaccinationEntity.builder()
                .id(UuidUtility.generateRandomUUID())
                .doseRound(doseRound)
                .doseDescription(doseDescription)
                .vaccinationDate(vaccinationDate)
                .vaccinationFacility(vaccinationFacility)
                .vaccinationManufacturer(vaccinationManufacturer)
                .vaccineProductName(vaccineProductName)
                .vaccineLotNumber(vaccineLotNumber)
                .disease(disease)
                .vaccine(vaccine)
                .build();
    }
}
