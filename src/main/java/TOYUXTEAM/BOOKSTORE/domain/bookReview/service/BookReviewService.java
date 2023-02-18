package TOYUXTEAM.BOOKSTORE.domain.bookReview.service;

import TOYUXTEAM.BOOKSTORE.domain.bookReview.dto.UpdateBookReviewReq;
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
    private final BookReviewRepository bookReviewRepository;
    private final UserRepository userRepository;

    public void write(WriteBookReviewReq writeBookReviewReq) {
        User user = User.builder()
                .id("testId")
                .email("gkfktkrh153@naver.com")
                .name("seungYong")
                .password("123123")
                .role("manager")
                .build();

        userRepository.save(user);
        BookReview bookReview = BookReview.builder()
                .title(writeBookReviewReq.getTitle())
                .content(writeBookReviewReq.getContent())
                .author(writeBookReviewReq.getAuthor())
                .store(writeBookReviewReq.getStore())
                .month(writeBookReviewReq.getDay())
                .day(writeBookReviewReq.getDay())
                .user(user)
                .build();


        bookReviewRepository.save(bookReview);
    }

    public void update(Long reviewId, UpdateBookReviewReq updateBookReviewReq) {
        BookReview bookReview = bookReviewRepository.findById(reviewId).orElse(null);
        bookReview.update(updateBookReviewReq);

    }

    public void delete(Long id) {
        BookReview bookReview = bookReviewRepository.findById(id).orElse(null);
        bookReviewRepository.delete(bookReview);
    }
}
