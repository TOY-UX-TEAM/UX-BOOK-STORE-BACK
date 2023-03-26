package TOYUXTEAM.BOOKSTORE.domain.diary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DiaryDto {

    private Long diaryId;
    private String title;
    private String content;

    @JsonIgnore
    private Long userId;

    private LocalDate createdDate;

    @QueryProjection
    public DiaryDto(Long diaryId, String title, String content, Long userId, LocalDate createdDate) {
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
    }
}
