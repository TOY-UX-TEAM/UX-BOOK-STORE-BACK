package TOYUXTEAM.BOOKSTORE.domain.diary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiarySearchCond {

    private Long userId;
    private Long diaryId;
    private LocalDate localDate;

    public DiarySearchCond(Long userId) {
        this.userId = userId;
    }
    public DiarySearchCond(Long userId, LocalDate localDate) {
        this.userId = userId;
        this.localDate = localDate;
    }
}
