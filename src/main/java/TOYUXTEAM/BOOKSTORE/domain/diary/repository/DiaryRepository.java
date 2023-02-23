package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("select d from Diary d join fetch d.user where d.user.user_id = :userId and d.createdDate = :createdDate")
    List<DiaryDto> findByIdDiaryForDate(@Param("userId") Long id, @Param("createdDate") LocalDate date);

    @Query("select d from Diary d where d.diary_id = :diaryId")
    DiaryDto findByDiary(@Param("diaryId") Long diaryId);


}
