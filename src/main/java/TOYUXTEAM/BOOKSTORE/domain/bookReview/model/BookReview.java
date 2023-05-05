package TOYUXTEAM.BOOKSTORE.domain.bookReview.model;

import TOYUXTEAM.BOOKSTORE.domain.base.BaseTimeEntity;
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
public class BookReview extends BaseTimeEntity{

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public BookReview(String title, String content, String author,String store, User user) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.store = store;
        this.user = user;
        user.getBookReviews().add(this);
    }

    public void update(UpdateBookReviewReq updateBook){
        this.title = updateBook.getTitle();
        this.content = updateBook.getContent();
        this.author = updateBook.getAuthor();
        this.store = updateBook.getStore();
    }
}
