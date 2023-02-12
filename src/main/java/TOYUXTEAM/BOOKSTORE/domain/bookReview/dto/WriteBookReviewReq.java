package TOYUXTEAM.BOOKSTORE.domain.bookReview.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriteBookReviewReq {

    private String title;
    private String content;
    private String store;
    private String author;
    private String month;
    private String day;

}

