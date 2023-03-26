package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiaryRepositoryTest {

    @Autowired DiaryRepository diaryRepository;
    @Autowired DiaryService diaryService;
    @Autowired UserRepository userRepository;


}