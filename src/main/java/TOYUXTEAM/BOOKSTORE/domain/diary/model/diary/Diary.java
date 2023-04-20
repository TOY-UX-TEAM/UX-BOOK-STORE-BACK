package TOYUXTEAM.BOOKSTORE.domain.diary.model.diary;

import TOYUXTEAM.BOOKSTORE.domain.base.BaseTimeEntity;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.content.DiaryContent;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseTimeEntity {

    /**
     * @GeneratedValue 옵션 선택시 고려할 점
     * 라클에서는 컬럼 값의 증가를 위해 Sequence를 사용하는데,
     * 거기에 맞춰 SEQUENCE를 사용할 수 있고, Mysql에서는 AUTO_INCREMENT 옵션으로 자동 증가 값을 사용하고,
     * IDENTITY를 사용하시면 됩니다. IDENTITY는 DB에 자동 증가 설정을 시키는 옵션이고,
     * SEQUENCE는 DB의 Object를 사용하여 Unique한 값을 순서대로 생성해 줄 수 있는 옵션입니다.
     * TABLE은 키 생성을 위한 전용의 테이블을 생성하여 SEQUENCE 오브젝트처럼 사용하기 위한 옵션입니다.
     */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_content_id")
    private DiaryContent diaryContent;

    public Diary(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Diary(String title, String content, User user, DiaryContent diaryContent) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.diaryContent = diaryContent;
    }

    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
