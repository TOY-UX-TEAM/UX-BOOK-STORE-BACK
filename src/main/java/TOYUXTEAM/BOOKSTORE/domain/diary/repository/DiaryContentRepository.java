package TOYUXTEAM.BOOKSTORE.domain.diary.repository;

import TOYUXTEAM.BOOKSTORE.domain.diary.model.content.DiaryContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryContentRepository extends JpaRepository<DiaryContent, Long> {
}
