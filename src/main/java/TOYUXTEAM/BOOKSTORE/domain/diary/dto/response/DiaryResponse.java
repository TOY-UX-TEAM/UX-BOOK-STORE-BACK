package TOYUXTEAM.BOOKSTORE.domain.diary.dto.response;

import TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.Diary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryResponse {

    private Long diaryId;
    private String title;
    private String content;

    @JsonIgnore
    private Long userId;

    private LocalDate createdDate;

    private String fileName;

    @Builder
    @QueryProjection
    public DiaryResponse(Long diaryId, String title, String content, Long userId, LocalDate createdDate) {
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
    }

    public static DiaryResponse of(final Diary diary) {
        return DiaryResponse.builder()
                .diaryId(diary.getId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .userId(diary.getUser().getUser_id())
                .createdDate(diary.getCreatedDate())
                .build();
    }
}
