package kr.co.vacgom.persistence.disease.domain;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "TB_DISEASE")
@Getter
@NoArgsConstructor
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class DiseaseEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISEASE_ID")
    @Comment("[Not Null] 질병 Id")
    private Long id;

    @Column(nullable = false)
    @Comment("[Not Null] 질병 Id")
    private String diseaseName;

    @Column(nullable = false)
    @Comment("[Not Null] 질병 설명 정보")
    private String diseaseInfo;
}
