package TOYUXTEAM.BOOKSTORE.domain.diary.dto;

import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DiaryDto {

    private Long diaryId;
    private String title;
    private String content;

    @JsonIgnore
    private Long userId;

    private LocalDate createdDate;

    public DiaryDto(Diary diary) {
        this.diaryId = diary.getId();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.userId = diary.getUser().getUser_id();
        this.createdDate = diary.getCreatedDate();
    }

    @QueryProjection
    public DiaryDto(Long diaryId, String title, String content, Long userId, LocalDate createdDate) {
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
    }

    public void dtoInSetDate(Long diaryId, Long userId, LocalDate createdDate) {
        this.createdDate = createdDate;
        this.diaryId = diaryId;
        this.userId = userId;
    }
}
