package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiarySearchCond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryRepositoryCustom {

    DiaryDto findByDiaryId(Long diaryId);
    Page<DiaryDto> findByIdDiaries(DiarySearchCond cond, Pageable pageable);
    Page<DiaryDto> findByIdDiaryForDate(DiarySearchCond cond, Pageable pageable);
}
