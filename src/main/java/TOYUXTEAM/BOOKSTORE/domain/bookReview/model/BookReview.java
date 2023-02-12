package TOYUXTEAM.BOOKSTORE.domain.bookReview.model;

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

    @Column
    private String month;

    @Column
    private String day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public BookReview(String title, String content, String author, String month, String day, User user) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.month = month;
        this.day = day;
        this.user = user;
    }
}
