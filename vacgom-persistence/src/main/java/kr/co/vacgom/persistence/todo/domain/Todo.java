package kr.co.vacgom.persistence.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kr.co.vacgom.persistence.member.Baby;
import kr.co.vacgom.persistence.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "TB_TODO")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String title;

    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean status;

    public void updateMemberAndStatus(Member member, boolean status) {
        if (status) {
            this.member = member;
        } else {
            this.member = null;
        }
        this.status = status;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateMemo(String memo) {
        this.memo = memo;
    }
}
