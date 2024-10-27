package kr.co.vacgom.persistence.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.vacgom.persistence.global.entity.BaseEntity;
import kr.co.vacgom.persistence.member.constants.Sex;
import kr.co.vacgom.persistence.todo.entity.TodoEntity;
import kr.co.vacgom.persistence.vaccination.domain.Inoculation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Table(name = "TB_BABY")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BabyEntity extends BaseEntity {

    @Id
    @Column(name = "BABY_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    @OneToMany(
            mappedBy = "baby",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Inoculation> inoculations = new ArrayList<>();

    @OneToMany(
            mappedBy = "baby",
            orphanRemoval = true,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<TodoEntity> todos = new ArrayList<>();
}

