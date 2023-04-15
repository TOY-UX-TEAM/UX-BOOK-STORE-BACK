package TOYUXTEAM.BOOKSTORE.domain.diary.controller;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/{id}/home")  // page 2
    public ResponseEntity<Page<DiaryResponse>> getDiariesHome(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return ResponseEntity.ok().body(diaryService.findDiaries(id, offset, 2));
    }
  
    @GetMapping("/{id}/list")  // page 9
    public ResponseEntity<Page<DiaryResponse>> getDiariesDateBefore(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return ResponseEntity.ok().body(diaryService.findDiaries(id, offset, 9));
    }


    @GetMapping("/{id}/list/{month}/{day}/diary") // page 9
    public ResponseEntity<Page<DiaryResponse>> getDiariesForDate(@PathVariable("id") Long id,
                                            @PathVariable("month") Long month, @PathVariable("day") Long day, @RequestParam("page") int offset) {
        return ResponseEntity.ok().body(diaryService.findDiariesForDate(id, month, day, offset));
    }

    @GetMapping("/diary")
    public ResponseEntity<DiaryResponse> getDiary(@RequestParam("diaryid") Long diaryId) {
        return ResponseEntity.ok().body(diaryService.findDiary(diaryId));
    }

    @PostMapping("/{id}/newdiary")
    public ResponseEntity<DiaryResponse> createDiary(@PathVariable("id") Long id, @RequestBody @Valid DiaryRequest diaryRequest) {
        return ResponseEntity.ok().body(diaryService.createDiary(id, diaryRequest));
    }

    @PatchMapping("/{id}/diary")
    public ResponseEntity<DiaryResponse> modifyDiary(@RequestParam("diaryid") Long diaryId,
                                @PathVariable("id") Long id, @RequestBody @Valid DiaryRequest diaryRequest) {
        return ResponseEntity.ok().body(diaryService.modifyDiary(id, diaryId, diaryRequest));
    }

    @DeleteMapping("/{id}/diary")
    public ResponseEntity<DiaryResponse> deleteDiary(@PathVariable("id") Long id, @RequestParam("diaryid") Long diaryId){
        return ResponseEntity.ok().body(diaryService.deleteDiary(id, diaryId));
    }
}
