package kr.co.vacgom.persistence.hospital;

import jakarta.persistence.*;
import kr.co.vacgom.persistence.vaccination.domain.Vaccination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_vaccination_hospital")
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class VaccinationHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaccination_id")
    private Vaccination vaccination;
}
