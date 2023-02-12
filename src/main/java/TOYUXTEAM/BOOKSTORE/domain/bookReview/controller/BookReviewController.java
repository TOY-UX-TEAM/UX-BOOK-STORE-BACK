package TOYUXTEAM.BOOKSTORE.domain.bookReview.controller;


import TOYUXTEAM.BOOKSTORE.domain.bookReview.dto.WriteBookReviewReq;
import TOYUXTEAM.BOOKSTORE.domain.bookReview.service.BookReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookReviewController {

    private BookReviewService bookReviewService;

    @PostMapping("/book-review")
    public void writeBookReview(@RequestParam("userid") String userId, @RequestBody WriteBookReviewReq writeBookReviewReq){

        bookReviewService.write(Long.parseLong(userId), writeBookReviewReq);

    }
}
