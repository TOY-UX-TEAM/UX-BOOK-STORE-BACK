package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryWithFileService {

    protected final DiaryRepository diaryRepository;
    protected final UserRepository userRepository;
    protected final DiaryContentRepository diaryContentRepository;

    public DiaryWithFileResponse create(Long userId, DiaryRequest diaryRequest) throws IOException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("회원을 찾을 수 없습니다."));

        DiaryContent diaryContent = DiaryContent.builder()
                .name(diaryRequest.getFile().getOriginalFilename())
                .type(diaryRequest.getFile().getContentType())
                .fileData(diaryRequest.getFile().getBytes())
                .build();
        Diary diary = new Diary(diaryRequest.getTitle(), diaryRequest.getContent(), user, diaryContent);
        user.diariesAdd(diary);

        diaryContentRepository.save(diaryContent);
        diaryRepository.save(diary);

        return DiaryWithFileResponse.of(diary);
    }

    public DiaryWithFileResponse modify(Long userId, Long diaryId, DiaryRequest diaryRequest) throws IOException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("회원을 찾을 수 없습니다."));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("일기를 찾을 수 없습니다."));

        DiaryContent diaryContent = DiaryContent.builder()
                .name(diaryRequest.getFile().getOriginalFilename())
                .type(diaryRequest.getFile().getContentType())
                .fileData(diaryRequest.getFile().getBytes())
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

        return DiaryWithFileResponse.of(diary);
    }
}
