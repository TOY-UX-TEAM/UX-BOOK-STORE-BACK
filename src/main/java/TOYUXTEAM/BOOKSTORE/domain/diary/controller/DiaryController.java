package TOYUXTEAM.BOOKSTORE.domain.diary.controller;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.dto.response.DiaryWithFileResponse;
import TOYUXTEAM.BOOKSTORE.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/home")  // page 2
    public ResponseEntity<Page<DiaryWithFileResponse>> getDiariesHome(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return ResponseEntity.ok().body(diaryService.findAll(id, offset, 2));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/list")  // page 9
    public ResponseEntity<Page<DiaryWithFileResponse>> getDiariesDateBefore(@PathVariable("id") Long id, @RequestParam("page") int offset) {
        return ResponseEntity.ok().body(diaryService.findAll(id, offset, 9));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/list/{month}/{day}/diary") // page 9
    public ResponseEntity<Page<DiaryWithFileResponse>> getDiariesForDate(@PathVariable("id") Long id,
                                            @PathVariable("month") Long month, @PathVariable("day") Long day, @RequestParam("page") int offset) {
        return ResponseEntity.ok().body(diaryService.findByDate(id, month, day, offset));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/diary")
    public ResponseEntity<DiaryWithFileResponse> getDiary(@RequestParam("diaryid") Long diaryId) {
        return ResponseEntity.ok().body(diaryService.find(diaryId));
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/{id}/new")
//    public ResponseEntity<DiaryResponse> createDiary(@PathVariable("id") Long id, @RequestBody @Valid DiaryRequest diaryRequest) {
//        return ResponseEntity.ok().body(diaryService.create(id, diaryRequest));
//    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/new")
    public ResponseEntity<DiaryWithFileResponse> createDiary(@PathVariable("id") Long id,
                                                                     @RequestPart("title") @NotNull String title, @RequestPart("content") @NotNull String content,
                                                                     @RequestPart("file") MultipartFile file) throws IOException {
        DiaryRequest diaryRequest = new DiaryRequest(title, content);
        return ResponseEntity.ok().body(diaryService.create(id, diaryRequest, file));
    }


//    @PreAuthorize("isAuthenticated()")
//    @PatchMapping("/{id}/diary")
//    public ResponseEntity<DiaryResponse> modifyDiary(@RequestParam("diaryid") Long diaryId,
//                                                     @PathVariable("id") Long id, @RequestBody @Valid DiaryRequest diaryRequest) {
//        return ResponseEntity.ok().body(diaryService.modify(id, diaryId, diaryRequest));
//    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/diary")
    public ResponseEntity<DiaryWithFileResponse> showModify(@PathVariable Long id, @RequestParam("diaryid") Long diaryId) {
        return ResponseEntity.ok().body(diaryService.showModify(id, diaryId));
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}/diary")
    public ResponseEntity<DiaryWithFileResponse> modify(@RequestParam("diaryid") Long diaryId, @PathVariable("id") Long id,
                                                        @RequestPart("title") @NotNull String title, @RequestPart("content") @NotNull String content,
                                                        @RequestPart("file") MultipartFile file) throws IOException {
        DiaryRequest diaryRequest = new DiaryRequest(title, content);
        return ResponseEntity.ok().body(diaryService.modify(id, diaryId, diaryRequest, file));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}/diary")
    public ResponseEntity<DiaryResponse> deleteDiary(@PathVariable("id") Long id, @RequestParam("diaryid") Long diaryId){
        return ResponseEntity.ok().body(diaryService.delete(id, diaryId));
    }
}
