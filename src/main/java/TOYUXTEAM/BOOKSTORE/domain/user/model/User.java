package TOYUXTEAM.BOOKSTORE.domain.user.model;


import TOYUXTEAM.BOOKSTORE.domain.bookReview.model.BookReview;
import TOYUXTEAM.BOOKSTORE.domain.diary.model.Diary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @Builder.Default
    private List<BookReview> bookReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @Builder.Default
    private List<Diary> diaries = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
