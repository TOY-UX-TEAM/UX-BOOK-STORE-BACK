package TOYUXTEAM.BOOKSTORE.domain.diary.model.content;

import TOYUXTEAM.BOOKSTORE.domain.diary.dto.request.DiaryRequest;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.UUID;

@Entity
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

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;

    public static DiaryContent of(final MultipartFile file) throws IOException {
        return DiaryContent.builder()
                .name(createStoreFileName(file.getOriginalFilename()))
                .type(file.getContentType())
                .fileData(file.getBytes())
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
