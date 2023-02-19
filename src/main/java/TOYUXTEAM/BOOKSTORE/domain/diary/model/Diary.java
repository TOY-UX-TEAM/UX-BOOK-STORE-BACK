package TOYUXTEAM.BOOKSTORE.domain.diary.model;


import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class) // 생성일, 수정일
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Diary extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long diary_id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setDiary(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
