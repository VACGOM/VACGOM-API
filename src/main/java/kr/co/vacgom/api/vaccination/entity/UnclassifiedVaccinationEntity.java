package kr.co.vacgom.api.vaccination.entity;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import kr.co.vacgom.api.baby.Baby;
import kr.co.vacgom.api.global.util.UuidBinaryConverter;
import kr.co.vacgom.api.global.util.UuidUtility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "TB_VACCINATION_UNCLASSIFIED")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class UnclassifiedVaccinationEntity {

    @Id
    @Convert(converter = UuidBinaryConverter.class)
    @Column(
            columnDefinition = "BINARY(16)",
            nullable = false,
            updatable = false
    )
    @Comment("[Not Null] 미분류 백신 접종 내역 엔티티 Id")
    private UUID id = UuidUtility.generateRandomUUID();

    @Column(nullable = false)
    @Comment("[Not Null] 미분류 백신 이름")
    private String name;

    @Column
    @Comment("[Nullable] 백신 접종 차수")
    private Long doseRound;

    @Column
    @Comment("[Nullable] 백신 접종 차수 및 접종 정보")
    private String doseRoundDescription;

    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Comment("[Not Null] 백신 접종 일자")
    private LocalDate vaccinatedAt;

    @Comment("[Nullable] 백신 접종 기관")
    private String facility;

    @Comment("[Nullable] 백신 제조사")
    private String manufacturer;

    @Comment("[Nullable] 판매 허가된 백신 정규 명칭")
    private String productName;

    @Comment("[Nullable] 백신 고유 로트번호")
    private String lotNumber;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BABY_ID")
    @Comment("[NotNull] 아기 Id")
    private Baby baby;
}
