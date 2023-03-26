package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryRepositoryCustom {
//
//    DiaryDto findByDiaryId(Long diaryId);
    Page<DiaryResponse> findByIdDiaries(DiarySearchCond cond, Pageable pageable);
    Page<DiaryResponse> findByIdDiaryForDate(DiarySearchCond cond, Pageable pageable);
}
