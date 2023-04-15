package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.exception.DiaryException;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryRepository;
import TOYUXTEAM.BOOKSTORE.domain.user.exception.UserException;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<DiaryResponse> findDiaries(Long userId, int offset, int pageSize) {
        Pageable pageable = PageRequest.of(offset, pageSize);
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId);
        return diaryRepository.findByIdDiaries(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public Page<DiaryResponse> findDiariesForDate(Long userId, Long month, Long day, int offset) {
        Pageable pageable = PageRequest.of(offset, 9);
        LocalDate date = LocalDate.of(LocalDateTime.now().getYear(), month.intValue(), day.intValue());
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId, date);
        return diaryRepository.findByIdDiaryForDate(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public DiaryResponse findDiary(Long diaryId) {
        return DiaryResponse.of(diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다.")));
    }

    public DiaryResponse createDiary(Long userId, DiaryRequest diaryRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));

        Diary diary = new Diary(diaryRequest.getTitle(), diaryRequest.getContent(), user);
        diaryRepository.save(diary);
        user.getDiaries().add(diary);

        return DiaryResponse.of(diary);
    }

    public DiaryResponse modifyDiary(Long userId, Long diaryId, DiaryRequest diaryRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));

        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다."));

        diary.diaryInSet(diaryRequest.getTitle(), diaryRequest.getContent());
        user.getDiaries().stream()
                .filter(d -> d.getId().equals(diaryId))
                .forEach(d -> d.diaryInSet(diaryRequest.getTitle(), diaryRequest.getContent()));

        return DiaryResponse.of(diary);
    }

    public DiaryResponse deleteDiary(Long userId, Long diaryId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));

        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다."));

        diaryRepository.delete(diary);
        user.getDiaries().remove(diary);

        return DiaryResponse.of(diary);
    }
}
