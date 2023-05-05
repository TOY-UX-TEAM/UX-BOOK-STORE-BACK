package TOYUXTEAM.BOOKSTORE.domain.bookReview.dto;

import TOYUXTEAM.BOOKSTORE.domain.bookReview.model.BookReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReviewRes {
    private String title;
    private String content;
    private String store;
    private String author;

    public BookReviewRes(BookReview bookReview) {
        title = bookReview.getTitle();
        content = bookReview.getContent();
        store = bookReview.getStore();
        author = bookReview.getAuthor();
    }
}
