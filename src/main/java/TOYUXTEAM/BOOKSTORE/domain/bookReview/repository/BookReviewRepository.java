package TOYUXTEAM.BOOKSTORE.domain.bookReview.repository;


import TOYUXTEAM.BOOKSTORE.domain.bookReview.model.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {


}
