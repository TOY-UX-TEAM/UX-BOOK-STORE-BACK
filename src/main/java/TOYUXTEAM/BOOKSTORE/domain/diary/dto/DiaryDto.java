package TOYUXTEAM.BOOKSTORE.domain.diary.dto;

import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiaryDto {

    private Long id;
    private String title;
    private String content;
    private User user;
    private LocalDateTime createdDate;

    public DiaryDto(Diary diary) {
        id = diary.getDiary_id();
        title = diary.getTitle();
        content = diary.getContent();
        user = diary.getUser();
    }

    public void dtoInSetDate(Long diaryId, User user, LocalDateTime createdDate) {
        this.createdDate = createdDate;
        this.id = diaryId;
        this.user = user;
    }

    public void dtoInSet(Long diaryId, User user) {
        this.id = diaryId;
        this.user = user;
    }
}
