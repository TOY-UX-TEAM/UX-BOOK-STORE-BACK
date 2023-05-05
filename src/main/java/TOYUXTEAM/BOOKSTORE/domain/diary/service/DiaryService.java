package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryWithFileResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.exception.DiaryException;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryRepository;
import TOYUXTEAM.BOOKSTORE.domain.user.exception.UserException;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    private final DiarySimpleService diarySimpleService;
    private final DiaryWithFileService diaryWithFileService;

    public DiaryWithFileResponse create(Long userId, DiaryRequest diaryRequest) throws IOException {
        if (diaryRequest.getFile().isEmpty()) {
            return diarySimpleService.create(userId, diaryRequest);
        } else {
            return diaryWithFileService.create(userId, diaryRequest);
        }
    }

    public DiaryWithFileResponse modify(Long userId, Long diaryId, DiaryRequest diaryRequest) throws IOException {
        if (diaryRequest.getFile().isEmpty()) {
            return diarySimpleService.modify(userId, diaryId, diaryRequest);
        } else {
            return diaryWithFileService.modify(userId, diaryId, diaryRequest);
        }
    }

    @Transactional(readOnly = true)
    public Page<DiaryWithFileResponse> findAll(Long userId, int offset, int pageSize) {

        userRepository.findById(userId).orElseThrow(() -> new UserException("회원을 찾을 수없습니다."));

        Pageable pageable = PageRequest.of(offset, pageSize);
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId);

        return diaryRepository.findByIdDiaries(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public Page<DiaryWithFileResponse> findByDate(Long userId, LocalDate date, int offset) {

        userRepository.findById(userId).orElseThrow(() -> new UserException("회원을 찾을 수없습니다."));

        Pageable pageable = PageRequest.of(offset, 9);
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId, date);

        return diaryRepository.findByIdDiaryForDate(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public DiaryWithFileResponse find(Long diaryId) {
        return DiaryWithFileResponse.of(diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("일기를 찾을 수 없습니다.")));
    }

    public DiaryWithFileResponse showModify(Long id, Long diaryId) {

        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("일기를 찾을 수 없습니다."));
        return DiaryWithFileResponse.of(diary);
    }

    public DiaryWithFileResponse delete(Long userId, Long diaryId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("회원을 찾을 수 없습니다."));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("일기를 찾을 수 없습니다."));

        diaryRepository.delete(diary);
        user.diariesDelete(diary);

        return DiaryWithFileResponse.of(diary);
    }
}
