package TOYUXTEAM.BOOKSTORE.domain.diary.dto;

import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import lombok.Data;

@Data
public class DiaryDto {

    private Long id;
    private String title;
    private String content;
    private User user;

    public DiaryDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public DiaryDto(Diary diary) {
        id = diary.getDiary_id();
        title = diary.getTitle();
        content = diary.getContent();
        user = diary.getUser();
    }
}
