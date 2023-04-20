package TOYUXTEAM.BOOKSTORE.domain.diary.model.content;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    public DiaryContent(String name, String type, byte[] fileData) {
        this.name = name;
        this.type = type;
        this.fileData = fileData;
    }
}
