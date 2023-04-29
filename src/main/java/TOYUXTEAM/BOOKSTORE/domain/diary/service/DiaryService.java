package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiarySearchCond;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryWithFileResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.exception.DiaryException;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.content.DiaryContent;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryContentRepository;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final DiaryContentRepository diaryContentRepository;


    @Transactional(readOnly = true)
    public Page<DiaryWithFileResponse> findAll(Long userId, int offset, int pageSize) {

        Pageable pageable = PageRequest.of(offset, pageSize);
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId);

        return diaryRepository.findByIdDiaries(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public Page<DiaryWithFileResponse> findByDate(Long userId, Long month, Long day, int offset) {

        Pageable pageable = PageRequest.of(offset, 9);
        LocalDate date = LocalDate.of(LocalDateTime.now().getYear(), month.intValue(), day.intValue());
        DiarySearchCond diarySearchCond = new DiarySearchCond(userId, date);

        return diaryRepository.findByIdDiaryForDate(diarySearchCond, pageable);
    }

    @Transactional(readOnly = true)
    public DiaryWithFileResponse find(Long diaryId) {
        return DiaryWithFileResponse.of(diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다.")));
    }



    public DiaryWithFileResponse create(Long userId, DiaryRequest diaryRequest, MultipartFile file) throws IOException {

        if (file.getSize() == 0) {
            return SimpleCreate(userId, diaryRequest);
        } else {
            return createWithFile(userId, diaryRequest, file);
        }
    }



    public DiaryWithFileResponse SimpleCreate(Long userId, DiaryRequest diaryRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));

        Diary diary = new Diary(diaryRequest.getTitle(), diaryRequest.getContent(), user, null);
        diaryRepository.save(diary);
        user.diariesAdd(diary);

        return DiaryWithFileResponse.of(diary.getId(), diary.getTitle(), diary.getContent(), diary.getUser().getUser_id(), diary.getCreatedDate(), null);
    }

    private DiaryWithFileResponse createWithFile(Long userId, DiaryRequest diaryRequest, MultipartFile file) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));

        DiaryContent diaryContent = DiaryContent.builder()
                .name(createStoreFileName(file.getOriginalFilename()))
                .type(file.getContentType())
                .fileData(file.getBytes())
                .build();
        Diary diary = new Diary(diaryRequest.getTitle(), diaryRequest.getContent(), user, diaryContent);
        user.diariesAdd(diary);

        diaryContentRepository.save(diaryContent);
        diaryRepository.save(diary);

        return DiaryWithFileResponse.of(diary);
    }

    public DiaryWithFileResponse showModify(Long id, Long diaryId) {

        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다."));
        return DiaryWithFileResponse.of(diary);
    }

    public DiaryWithFileResponse modify(Long userId, Long diaryId, DiaryRequest diaryRequest, MultipartFile file) throws IOException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다."));

        if (file.getSize() == 0) {
            if (diary.getDiaryContent() != null) {
                diaryContentRepository.delete(diary.getDiaryContent());
                diary.modify(diaryRequest.getTitle(), diaryRequest.getContent());
                user.diariesFileDelete(diaryId, diaryRequest);
            } else {
                diary.modify(diaryRequest.getTitle(), diaryRequest.getContent());
                user.diariesModify(diaryId, diaryRequest);
            }
        } else {
            DiaryContent diaryContent = DiaryContent.builder()
                    .name(createStoreFileName(file.getOriginalFilename()))
                    .type(file.getContentType())
                    .fileData(file.getBytes())
                    .build();
            if (diary.getDiaryContent() == null) {
                diaryContentRepository.save(diaryContent);
                diary.modifyWithFile(diaryRequest.getTitle(), diaryRequest.getContent(), diaryContent);
                user.diariesModifyWithFile(diaryId, diaryRequest, diaryContent);
            } else if (diary.getDiaryContent().getFileData() != diaryContent.getFileData()) {
                diaryContentRepository.save(diaryContent);
                diary.modifyWithFile(diaryRequest.getTitle(), diaryRequest.getContent(), diaryContent);
                user.diariesModifyWithFile(diaryId, diaryRequest, diaryContent);
            }
        }

        return DiaryWithFileResponse.of(diary);
    }

    public DiaryResponse delete(Long userId, Long diaryId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("존재하지 않는 회원입니다."));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("존재하지 않는 일기입니다."));

        diaryRepository.delete(diary);
        user.diariesDelete(diary);

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
