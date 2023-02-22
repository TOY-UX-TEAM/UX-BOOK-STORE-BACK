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
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public List<DiaryDto> findDiaries(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getDiaries().stream()
                .map(DiaryDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public DiaryDto saveDiary(Long userId, DiaryDto diaryDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        });

        Diary diary = new Diary(diaryDto.getTitle(), diaryDto.getContent(), user);
        diaryRepository.save(diary);

        diaryDto.dtoInSetDate(diaryRepository.findById(diary.getDiary_id()).get().getDiary_id(),
                user, diary.getCreatedDate());

        user.getDiaries().add(diary);
        return diaryDto;
    }

    @Transactional
    public void updateDiary(Long userId, Long diaryId, DiaryDto diaryDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        });
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 일기입니다.");
        });
        diary.diaryInSet(diaryDto.getTitle(), diaryDto.getContent());
        user.getDiaries().stream()
                .filter(u -> u.getDiary_id().equals(diaryId))
                .forEach(u -> u.diaryInSet(diaryDto.getTitle(), diaryDto.getContent()));
        diaryDto.dtoInSet(diaryId, user);
    }

    @Transactional
    public DiaryDto deleteDiary(Long userId, Long diaryId) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        });
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 일기입니다.");
        });
        DiaryDto diaryDto = new DiaryDto(diary);
        diaryRepository.delete(diary);
        user.getDiaries().remove(diary);
        return diaryDto;
    }
}
