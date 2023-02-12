package TOYUXTEAM.BOOKSTORE.domain.bookReview.model;

import TOYUXTEAM.BOOKSTORE.domain.user.model.User;

import javax.persistence.*;

@Entity
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


}
