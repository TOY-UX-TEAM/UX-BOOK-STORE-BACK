package TOYUXTEAM.BOOKSTORE.domain.bookReview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookReviewRes {
    private String title;
    private String content;
    private String store;
    private String author;
    private String month;
    private String day;
}
