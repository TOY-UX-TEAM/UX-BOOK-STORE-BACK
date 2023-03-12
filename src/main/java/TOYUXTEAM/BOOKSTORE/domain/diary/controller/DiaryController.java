package TOYUXTEAM.BOOKSTORE.domain.diary.controller;


import TOYUXTEAM.BOOKSTORE.domain.diary.dto.DiaryDto;
import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/{id}/home")  // page 2
    public ResponseEntity<Page<DiaryDto>> getDiariesHome(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return ResponseEntity.ok(diaryService.findDiaries(id, offset, 2));
    }

    @GetMapping("/{id}/list")  // page 9
    public ResponseEntity<Page<DiaryDto>> getDiariesDateBefore(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return ResponseEntity.ok(diaryService.findDiaries(id, offset, 9));
    }


    @GetMapping("/{id}/list/{month}/{day}") // page 9
    public ResponseEntity<Page<DiaryDto>> getDiariesForDate(@PathVariable("id") Long id,
                                            @PathVariable("month") Long month, @PathVariable("day") Long day, @RequestParam("page") int offset) {
        return ResponseEntity.ok(diaryService.findDiariesForDate(id, month, day, offset));
    }

    @GetMapping("/diary")
    public ResponseEntity<DiaryDto> getDiary(@RequestParam("diaryId") Long diaryId) {
        return ResponseEntity.ok(diaryService.findDiary(diaryId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DiaryDto> saveDiary(@PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {
        return ResponseEntity.ok(diaryService.saveDiary(id, diaryDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DiaryDto> modifyDiary(@RequestParam("diaryId") Long diaryId,
                                @PathVariable("id") Long id, @RequestBody @Validated DiaryDto diaryDto) {
        diaryService.updateDiary(id, diaryId, diaryDto);
        return ResponseEntity.ok(diaryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DiaryDto> deleteDiary(@PathVariable("id") Long id, @RequestParam("diaryId") Long diaryId){
        return ResponseEntity.ok(diaryService.deleteDiary(id, diaryId));
    }
}
