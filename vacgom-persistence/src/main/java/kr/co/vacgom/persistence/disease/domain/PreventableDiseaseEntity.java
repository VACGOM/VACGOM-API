package kr.co.vacgom.persistence.disease.domain;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.vaccination.entity.VaccineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "TB_PREVENTABLE_DISEASE")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class PreventableDiseaseEntity extends BaseEntity {

    @Id
    @Column(name = "PREVENTABLE_DISEASE_ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("[Not Null] 질병-백신 매핑 엔티티 Id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "DISEASE_ID")
    @Comment("[Not Null] 예방 가능한 질병 Id")
    private DiseaseEntity disease;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "VACCINE_ID")
    @Comment("[Not Null] 백신 Id")
    private VaccineEntity vaccine;

}
