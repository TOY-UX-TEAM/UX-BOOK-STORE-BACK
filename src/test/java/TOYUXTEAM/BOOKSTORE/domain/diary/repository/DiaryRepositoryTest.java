package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class DiaryRepositoryTest {

    @Autowired DiaryRepository diaryRepository;
    @Autowired DiaryService diaryService;
    @Autowired UserRepository userRepository;
    
    @Test
    @DisplayName("유저별 전체 일기 페이징 조회")
    public void findByIdDiaries() throws Exception {
        //given
        User user = new User();
        userRepository.save(user);

        Diary diary1 = new Diary("hi1", "hello", user);
        Diary diary2 = new Diary("hi2", "hello", user);
        Diary diary3 = new Diary("hi3", "hello", user);
        Diary diary4 = new Diary("hi4", "hello", user);

        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        diaryRepository.save(diary3);
        diaryRepository.save(diary4);

        //when
        Pageable pageable = PageRequest.of(0, 3);
        DiarySearchCond cond = new DiarySearchCond(user.getUser_id());

        Page<DiaryResponse> result = diaryRepository.findByIdDiaries(cond, pageable);

        //then
        Assertions.assertThat(result.getSize()).isEqualTo(3);
        Assertions.assertThat(result.getContent()).extracting("title").containsExactly("hi1","hi2","hi3");
    }

    @Test
    @DisplayName("유저별 날짜별 일기 페이징 조회")
    public void findByDate() throws Exception {
        //given
        User user = new User();
        userRepository.save(user);

        Diary diary1 = new Diary("hi1", "hello", user);
        Diary diary2 = new Diary("hi2", "hello", user);
        Diary diary3 = new Diary("hi3", "hello", user);
        Diary diary4 = new Diary("hi4", "hello", user);

        diaryRepository.save(diary1);
        diaryRepository.save(diary2);
        diaryRepository.save(diary3);
        diaryRepository.save(diary4);

        //when
        Pageable pageable = PageRequest.of(0, 3);
        LocalDate date = LocalDate.of(LocalDateTime.now().getYear(), 4, 18);
        DiarySearchCond cond = new DiarySearchCond(user.getUser_id(), date);

        Page<DiaryResponse> result = diaryRepository.findByIdDiaries(cond, pageable);

        //then
        Assertions.assertThat(result.getSize()).isEqualTo(3);
        Assertions.assertThat(result.getContent()).extracting("title").containsExactly("hi1","hi2","hi3");
    }

}