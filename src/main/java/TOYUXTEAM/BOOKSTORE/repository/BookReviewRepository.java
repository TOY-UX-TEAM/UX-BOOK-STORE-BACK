package TOYUXTEAM.BOOKSTORE.repository;


import TOYUXTEAM.BOOKSTORE.domain.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {


}
