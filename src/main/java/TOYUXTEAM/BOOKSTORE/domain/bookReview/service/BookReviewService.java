package TOYUXTEAM.BOOKSTORE.domain.bookReview.service;

import TOYUXTEAM.BOOKSTORE.domain.bookReview.dto.WriteBookReviewReq;
import TOYUXTEAM.BOOKSTORE.domain.bookReview.model.BookReview;
import TOYUXTEAM.BOOKSTORE.domain.bookReview.repository.BookReviewRepository;
import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import TOYUXTEAM.BOOKSTORE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookReviewService {
    private BookReviewRepository bookReviewRepository;
    private UserRepository userRepository;

    public void write(Long userId, WriteBookReviewReq writeBookReviewReq) {
        User user = userRepository.findById(userId).orElse(null);
        BookReview bookReview = BookReview.builder()
                .title(writeBookReviewReq.getTitle())
                .content(writeBookReviewReq.getContent())
                .author(writeBookReviewReq.getAuthor())
                .month(writeBookReviewReq.getDay())
                .day(writeBookReviewReq.getDay())
                .user(user)
                .build();


        bookReviewRepository.save(bookReview);
    }
}
