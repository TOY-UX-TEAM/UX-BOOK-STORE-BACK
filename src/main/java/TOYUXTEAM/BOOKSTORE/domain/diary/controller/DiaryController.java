package TOYUXTEAM.BOOKSTORE.domain.diary.controller;


import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/{id}/home")  // page 2
    public Page<DiaryDto> getDiariesHome(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return diaryService.findDiaries(id, offset, 2);
    }

    @GetMapping("/{id}/list")  // page 9
    public Page<DiaryDto> getDiariesDateBefore(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return diaryService.findDiaries(id, offset, 9);
    }


    @GetMapping("/{id}/list/{month}/{day}") // page 9
    public Page<DiaryDto> getDiariesForDate(@PathVariable("id") Long id,
                                            @PathVariable("month") Long month, @PathVariable("day") Long day, @RequestParam("page") int offset) {
        return diaryService.findDiariesForDate(id, month, day, offset);
    }

    @GetMapping("/diary")
    public DiaryDto getDiary(@RequestParam("diaryId") Long diaryId) {
        return diaryService.findDiary(diaryId);
    }

    @PostMapping("/{id}")
    public DiaryDto saveDiary(@PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {
        return diaryService.saveDiary(id, diaryDto);
    }

    @PatchMapping("/{id}")
    public DiaryDto modifyDiary(@RequestParam("diaryId") Long diaryId,
                                @PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {
        diaryService.updateDiary(id, diaryId, diaryDto);
        return diaryDto;
    }

    @DeleteMapping("/{id}")
    public DiaryDto deleteDiary(@PathVariable("id") Long id, @RequestParam("diaryId") Long diaryId){
        return diaryService.deleteDiary(id, diaryId);
    }
}
