package TOYUXTEAM.BOOKSTORE.domain.bookReview.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WriteBookReviewReq {

    private String title;
    private String content;
    private String store;
    private String author;
}

