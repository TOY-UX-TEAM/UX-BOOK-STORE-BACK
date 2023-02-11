package TOYUXTEAM.BOOKSTORE.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long user_id;

    @Column
    private String id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String role;

    @OneToMany(mappedBy = "user")
    private List<BookReview> bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries = new ArrayList<>();

}
