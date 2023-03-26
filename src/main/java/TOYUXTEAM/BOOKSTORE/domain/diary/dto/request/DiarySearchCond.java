package TOYUXTEAM.BOOKSTORE.domain.diary.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
public class DiarySearchCond {

    private Long userId;
    private Long diaryId;
    private LocalDate localDate;

    public DiarySearchCond(Long userId) {
        this.userId = userId;
    }

    public DiarySearchCond(Long diaryId, LocalDate localDate) {
        this.diaryId = diaryId;
        this.localDate = localDate;
    }
}
