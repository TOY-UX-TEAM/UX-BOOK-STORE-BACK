package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryRepository;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public Page<DiaryDto> findDiaries(Long userId, int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId);
        return diaryRepository.findByIdDiaries(diarySearchCond, pageable);
    }

    public Page<DiaryDto> findDiariesForDate(Long userId, Long month, Long day, int offset) {
        Pageable pageable = PageRequest.of(offset, 9);
        LocalDate date = LocalDate.of(LocalDateTime.now().getYear(), month.intValue(), day.intValue());
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId, date);
        return diaryRepository.findByIdDiaryForDate(diarySearchCond, pageable);
    }

    public DiaryDto findDiary(Long diaryId) {
        return diaryRepository.findByDiaryId(diaryId);
    }

    @Transactional
    public DiaryDto saveDiary(Long userId, DiaryDto diaryDto) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("존재하지 않는 회원입니다.");
        });

        Diary diary = new Diary(diaryDto.getTitle(), diaryDto.getContent(), user);
        diaryRepository.save(diary);

        diaryDto.dtoInSetDate(diary.getId(), user.getUser_id(), diary.getCreatedDate());

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
                .filter(u -> u.getId().equals(diaryId))
                .forEach(u -> u.diaryInSet(diaryDto.getTitle(), diaryDto.getContent()));
        diaryDto.dtoInSetDate(diaryId, user.getUser_id(), diary.getCreatedDate());
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
