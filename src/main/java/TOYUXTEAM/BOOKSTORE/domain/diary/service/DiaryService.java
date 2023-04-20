package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.exception.DiaryException;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.content.DiaryContent;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryRepository;
import TOYUXTEAM.BOOKSTORE.domain.user.exception.UserException;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<DiaryResponse> findAll(Long userId, int offset, int pageSize) {

        Pageable pageable = PageRequest.of(offset, pageSize);
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId);

        return diaryRepository.findByIdDiaries(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public Page<DiaryResponse> findByDate(Long userId, Long month, Long day, int offset) {

        Pageable pageable = PageRequest.of(offset, 9);
        LocalDate date = LocalDate.of(LocalDateTime.now().getYear(), month.intValue(), day.intValue());
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId, date);

        return diaryRepository.findByIdDiaryForDate(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public DiaryResponse find(Long diaryId) {
        return DiaryResponse.of(diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다.")));
    }

    public DiaryResponse create(Long userId, DiaryRequest diaryRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));

        Diary diary = new Diary(diaryRequest.getTitle(), diaryRequest.getContent(), user);
        diaryRepository.save(diary);
        user.getDiaries().add(diary);

        return DiaryResponse.of(diary);
    }

    public DiaryResponse createWithFile(Long userId, DiaryRequest diaryRequest) throws IOException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));

        DiaryContent diaryContent = DiaryContent.builder()
                .name(createStoreFileName(diaryRequest.getFile().getOriginalFilename()))
                .type(diaryRequest.getFile().getContentType())
                .fileData(diaryRequest.getFile().getBytes())
                .build();

        Diary diary = new Diary(diaryRequest.getTitle(), diaryRequest.getContent(), user, diaryContent);

        diaryRepository.save(diary);
        user.getDiaries().add(diary);

        return DiaryResponse.of(diary);
    }

    public DiaryResponse modify(Long userId, Long diaryId, DiaryRequest diaryRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다."));

        diary.modify(diaryRequest.getTitle(), diaryRequest.getContent());
        user.getDiaries().stream()
                .filter(d -> d.getId().equals(diaryId))
                .forEach(d -> d.modify(diaryRequest.getTitle(), diaryRequest.getContent()));

        return DiaryResponse.of(diary);
    }

    public DiaryResponse delete(Long userId, Long diaryId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다."));

        diaryRepository.delete(diary);
        user.getDiaries().remove(diary);

        return DiaryResponse.of(diary);
    }

    private String createStoreFileName(String originalFilename) {

        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;
    }
    private String extractExt(String originalFilename) {

        int pos = originalFilename.lastIndexOf(".");

        return originalFilename.substring(pos + 1);
    }
}
