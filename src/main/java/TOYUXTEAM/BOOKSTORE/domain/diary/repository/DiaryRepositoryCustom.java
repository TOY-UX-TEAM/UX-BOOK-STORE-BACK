package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryWithFileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiaryRepositoryCustom {

    Page<DiaryWithFileResponse> findByIdDiaries(DiarySearchCond cond, Pageable pageable);
    Page<DiaryWithFileResponse> findByIdDiaryForDate(DiarySearchCond cond, Pageable pageable);
}
