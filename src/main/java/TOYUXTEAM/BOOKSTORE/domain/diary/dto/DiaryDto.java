package TOYUXTEAM.BOOKSTORE.domain.diary.dto;

import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DiaryDto {

    private Long id;
    private String title;
    private String content;

    @JsonIgnore
    private User user;

    private LocalDate createdDate;

    public DiaryDto(Diary diary) {
        id = diary.getDiary_id();
        title = diary.getTitle();
        content = diary.getContent();
        user = diary.getUser();
        createdDate = diary.getCreatedDate();
    }

    public void dtoInSetDate(Long diaryId, User user, LocalDate createdDate) {
        this.createdDate = createdDate;
        this.id = diaryId;
        this.user = user;
    }

    public void dtoInSet(Long diaryId, User user) {
        this.id = diaryId;
        this.user = user;
    }
}
