package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class DiaryServiceTest {

    @Autowired DiaryService diaryService;
    @Autowired UserRepository userRepository;

    @Test
    @DisplayName("일기 추가")
    public void create() throws Exception {
        //given
        User user = new User();
        userRepository.save(user);

        DiaryRequest diaryRequest = new DiaryRequest("hi1", "hello");
        DiaryResponse diaryResponse = diaryService.create(user.getUser_id(), diaryRequest);

        //when
        DiaryResponse findDiary = diaryService.find(diaryResponse.getDiaryId());

        //then
        Assertions.assertThat(findDiary.getTitle()).isEqualTo("hi1");
        Assertions.assertThat(findDiary.getContent()).isEqualTo("hello");
    }

    @Test
    @DisplayName("일기 수정")
    public void modify() throws Exception {
        //given
        User user = new User();
        userRepository.save(user);

        DiaryRequest diaryRequest = new DiaryRequest("hi1", "hello");
        DiaryResponse diaryResponse = diaryService.create(user.getUser_id(), diaryRequest);

        //when
        DiaryRequest modifyData = new DiaryRequest("hi2323", "hello123");

        DiaryResponse modify = diaryService.modify(user.getUser_id(), diaryResponse.getDiaryId(), modifyData);
        DiaryResponse findDiary = diaryService.find(modify.getDiaryId());

        //then
        Assertions.assertThat(modify.getTitle()).isEqualTo(findDiary.getTitle());
        Assertions.assertThat(modify.getContent()).isEqualTo(findDiary.getContent());
    }

    @Test
    @DisplayName("일기 삭제")
    public void delete() throws Exception {
        //given
        User user = new User();
        userRepository.save(user);

        DiaryRequest diaryRequest = new DiaryRequest("hi1324324", "hello");
        DiaryResponse diaryResponse = diaryService.create(user.getUser_id(), diaryRequest);

        //when
        DiaryResponse delete = diaryService.delete(user.getUser_id(), diaryResponse.getDiaryId());

        //then
        Assertions.assertThat(delete.getTitle()).isEqualTo("hi1324324");
        Assertions.assertThat(delete.getContent()).isEqualTo("hello");

    }
}