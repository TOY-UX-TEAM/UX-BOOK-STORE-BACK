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
public class DiaryWithFileResponse {

    private Long diaryId;
    private String title;
    private String content;

    @JsonIgnore
    private Long userId;

    private LocalDate createdDate;

    private String filePath;

    @Builder
    @QueryProjection
    public DiaryWithFileResponse(Long diaryId, String title, String content, Long userId, LocalDate createdDate, String filePath) {
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
        this.filePath = filePath;
    }

    public static DiaryWithFileResponse of(final Diary diary) {
        return DiaryWithFileResponse.builder()
                .diaryId(diary.getId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .filePath(null)
                .build();
    }

    public static DiaryWithFileResponse of(final Diary diary, String filePath) {
        return DiaryWithFileResponse.builder()
                .diaryId(diary.getId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .createdDate(diary.getCreatedDate())
                .filePath(filePath)
                .build();
    }
}
