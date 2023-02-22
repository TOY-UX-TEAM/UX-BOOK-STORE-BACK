package TOYUXTEAM.BOOKSTORE.domain.diary.model;


import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
//@EntityListeners(AuditingEntityListener.class) // 생성일, 수정일
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Diary{

    @Id @GeneratedValue
    private Long diary_id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Diary(String title, String content, User user, LocalDateTime dateTime) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdDate = dateTime;
        //user.getDiaries().add(this);

    }

    public void diaryInSet(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
