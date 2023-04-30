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

    private String fileName;

    @Builder
    @QueryProjection
    public DiaryWithFileResponse(Long diaryId, String title, String content, Long userId, LocalDate createdDate, String fileName) {
        this.diaryId = diaryId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdDate = createdDate;
        this.fileName = fileName;
    }

    public static DiaryWithFileResponse of(final Diary diary) {
        return DiaryWithFileResponse.builder()
                .diaryId(diary.getId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .userId(diary.getUser().getUser_id())
                .createdDate(diary.getCreatedDate())
                .fileName(diary.getDiaryContent() != null ? diary.getDiaryContent().getName() : null)
                .build();
    }

}
