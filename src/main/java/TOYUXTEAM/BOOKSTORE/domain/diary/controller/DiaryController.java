package TOYUXTEAM.BOOKSTORE.domain.diary.controller;


import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/{id}/diary")
    public List<DiaryDto> getDiaries(@PathVariable("id") Long id) {
        return diaryService.findDiaries(id);
    }

    @GetMapping("/{id}/diary/{month}/{day}")
    public List<DiaryDto> getDiariesForDate(@PathVariable("id") Long id,
                                            @PathVariable("month") Long month, @PathVariable("day") Long day) {
        return diaryService.findDiariesForDate(id, month, day);
    }

    @GetMapping("/{id}/diary/{diaryId}")
    public DiaryDto getDiary(@PathVariable("id") Long id, @PathVariable(value = "diaryId") Long diaryId) {
        return diaryService.findDiary(id, diaryId);
    }

    @PostMapping("/{id}")
    public DiaryDto writeDiary(@PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {
        return diaryService.saveDiary(id, diaryDto);
    }

    @PatchMapping("/{id}")
    public DiaryDto updateDiary(@RequestParam("diaryId") Long diaryId,
                                @PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {
        diaryService.updateDiary(id, diaryId, diaryDto);
        return diaryDto;
    }

    @DeleteMapping("/{id}")
    public DiaryDto deleteDiary(@PathVariable("id") Long id, @RequestParam("diaryId") Long diaryId){
        return diaryService.deleteDiary(id, diaryId);
    }
}
