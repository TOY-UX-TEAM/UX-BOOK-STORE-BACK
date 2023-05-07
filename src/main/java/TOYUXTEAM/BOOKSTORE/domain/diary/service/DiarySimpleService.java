package TOYUXTEAM.BOOKSTORE.domain.diary.service;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryWithFileResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.exception.DiaryException;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.diary.Diary;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryContentRepository;
import TOYUXTEAM.BOOKSTORE.domain.diary.repository.DiaryRepository;
import TOYUXTEAM.BOOKSTORE.domain.user.exception.UserNotFoundException;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;

import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiarySimpleService {

    protected final DiaryRepository diaryRepository;
    protected final UserRepository userRepository;
    protected final DiaryContentRepository diaryContentRepository;

    public DiaryWithFileResponse create(Long userId, DiaryRequest diaryRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원을 찾을 수없습니다."));

        Diary diary = new Diary(diaryRequest.getTitle(), diaryRequest.getContent(), user, null);
        diaryRepository.save(diary);
        user.diariesAdd(diary);

        return DiaryWithFileResponse.of(diary);
    }

    public DiaryWithFileResponse modify(Long userId, Long diaryId, DiaryRequest diaryRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원을 찾을 수없습니다."));
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> new DiaryException("일기를 찾을 수 없습니다."));

        if (diary.getDiaryContent() != null) {
            diaryContentRepository.delete(diary.getDiaryContent());
            diary.modify(diaryRequest.getTitle(), diaryRequest.getContent());
            user.diariesFileDelete(diaryId, diaryRequest);
        } else {
            diary.modify(diaryRequest.getTitle(), diaryRequest.getContent());
            user.diariesModify(diaryId, diaryRequest);
        }

        return DiaryWithFileResponse.of(diary);
    }
}
