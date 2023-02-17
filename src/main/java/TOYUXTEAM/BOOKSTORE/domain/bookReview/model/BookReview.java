package TOYUXTEAM.BOOKSTORE.domain.bookReview.model;

import TOYUXTEAM.BOOKSTORE.domain.bookReview.dto.UpdateBookReviewReq;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
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
        user.getBookReviews().add(this);
    }

    public void update(UpdateBookReviewReq updateBook){
        checkNotNull(updateBook.getTitle(), "제목은 Null 일 수 없습니다");
        checkNotNull(updateBook.getContent(), "내용은 Null 일 수 없습니다.");
        checkNotNull(updateBook.getAuthor(), "author는 Null 일 수 없습니다.");
        checkNotNull(updateBook.getStore(), "store는 Null 일 수 없습니다.");
        checkNotNull(updateBook.getMonth(), "month는 Null 일 수 없습니다.");
        checkNotNull(updateBook.getDay(), "day는 Null 일 수 없습니다.");

        this.title = updateBook.getTitle();
        this.content = updateBook.getContent();
        this.author = updateBook.getAuthor();
        this.store = updateBook.getStore();
        this.month = updateBook.getMonth();
        this.day = updateBook.getDay();
    }
}
