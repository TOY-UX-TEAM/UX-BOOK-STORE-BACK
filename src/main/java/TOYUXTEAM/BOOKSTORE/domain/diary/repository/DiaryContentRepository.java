package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.model.content.DiaryContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryContentRepository extends JpaRepository<DiaryContent, Long> {

    Optional<DiaryContent> findByName(String fileName);
}
