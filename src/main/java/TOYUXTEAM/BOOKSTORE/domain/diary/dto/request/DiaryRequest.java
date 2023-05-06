package TOYUXTEAM.BOOKSTORE.domain.diary.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private MultipartFile file;

    // test 용
    public DiaryRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public DiaryRequest(String title, String content, MultipartFile file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }
}
