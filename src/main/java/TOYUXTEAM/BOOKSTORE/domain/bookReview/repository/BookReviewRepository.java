package TOYUXTEAM.BOOKSTORE.domain.bookReview.repository;


import TOYUXTEAM.BOOKSTORE.domain.bookReview.model.BookReview;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {
    List<BookReview> findByUserId(Long userId);

/*    List<BookReview> findAllByUserIdAndDay(@Param("userId") Long userId,@Param("date") String date);*/
}
