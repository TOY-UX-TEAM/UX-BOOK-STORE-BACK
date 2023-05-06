package TOYUXTEAM.BOOKSTORE.domain.bookReview.controller;

import TOYUXTEAM.BOOKSTORE.domain.bookReview.dto.WriteBookReviewReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookReviewControllerTest {
    @Autowired
    private MockMvc mvc;
    //
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("책 리뷰 작성 ")
    @WithUserDetails("user1") // "user1" 값은 등록된 Bean 중 UserDetailsService를 찾아 loadUserByUsername의 파라미터 값으로 바인딩된다.
    public void test1() throws Exception {

        //
        WriteBookReviewReq writeBookReview = WriteBookReviewReq.builder()
                .title("testTitle")
                .content("testContent")
                .store("testStore")
                .author("testAuthor")
                .build();
        String json = objectMapper.writeValueAsString(writeBookReview);

        ResultActions resultActions = mvc
                .perform(post("/book-review")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(handler().handlerType(BookReviewController.class))
                .andExpect(handler().methodName("writeBookReview"))
                .andExpect(status().is2xxSuccessful());

    }
}