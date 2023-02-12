package TOYUXTEAM.BOOKSTORE.domain.bookReview.model;

import TOYUXTEAM.BOOKSTORE.domain.bookReview.dto.UpdateBookReviewReq;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class BookReview {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String author;

    @Column String store;

    @Column
    private String month;


    @Column
    private String day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public BookReview(String title, String content, String author,String store, String month, String day, User user) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.store = store;
        this.month = month;
        this.day = day;
        this.user = user;
    }

    public void update(UpdateBookReviewReq updateBook){
        this.title = updateBook.getTitle();
        this.content = updateBook.getContent();
        this.author = updateBook.getAuthor();
        this.store = updateBook.getStore();
        this.month = updateBook.getMonth();
        this.day = updateBook.getDay();
    }
}
