package TOYUXTEAM.BOOKSTORE.domain.diary.controller;


import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

    private final UserRepository userRepository;

    private final DiaryService diaryService;

    @PostMapping("/{id}/diary")
    public DiaryDto writeDiary(@PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {

        return diaryService.saveDiary(id, diaryDto);
    }

    @PatchMapping("/{id}/diary")
    public DiaryDto updateDiary(@PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {
        diaryService.updateDiary(id, diaryDto);
        return diaryDto;
    }

    @DeleteMapping("/{id}/diary")
//    public ResponseEntity<Object> deleteDiary()


    @GetMapping("/{id}")
    public List<DiaryDto> getDiaries(@PathVariable("id") Long id) {
        List<DiaryDto> collect = userRepository.findById(id).get().getDiaries().stream()
                .map(DiaryDto::new)
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
        return collect;
    }



    @PostConstruct
    public void init() {
        for (long i = 0L; i < 10; i++) {
            userRepository.save(new User("id_"+i, "user_" + i, "password_"+i, "role_" + i,"user_" + i));
        }
    }
}
