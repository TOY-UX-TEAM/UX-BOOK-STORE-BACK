package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryRepository;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveDiary(Long userId, Diary diary) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        });
        diaryRepository.save(diary);
        user.getDiaries().add(diary);
    }

    @Transactional
    public void updateDiary(Long userId, DiaryDto diaryDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        });
        Diary diary = diaryRepository.findById(diaryDto.getId()).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 일기입니다.");
        });
        diary.setDiary(diaryDto.getTitle(), diaryDto.getContent());
    }

    @Transactional
    public void deleteDiary(Long userId, DiaryDto diaryDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        });
        Diary diary = diaryRepository.findById(diaryDto.getId()).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 일기입니다.");
        });
        user.getDiaries().remove(diary);
        diaryRepository.delete(diary);
    }
}
