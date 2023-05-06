package TOYUXTEAM.BOOKSTORE.domain.diary.model.content;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Entity
@Slf4j
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryContent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    private String filePath;

    public static DiaryContent of(final MultipartFile file, String filePath) throws IOException {

        String storeFileName = createStoreFileName(file.getOriginalFilename());

        log.info("file path: {}, {}", filePath, storeFileName);
        file.transferTo(new File(filePath + storeFileName));

        return DiaryContent.builder()
                .name(storeFileName)
                .type(file.getContentType())
                .filePath(filePath + storeFileName)
                .build();
    }

    private static String createStoreFileName(String originalFilename) {

        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;
    }

    private static String extractExt(String originalFilename) {

        int pos = originalFilename.lastIndexOf(".");

        return originalFilename.substring(pos + 1);
    }
}
