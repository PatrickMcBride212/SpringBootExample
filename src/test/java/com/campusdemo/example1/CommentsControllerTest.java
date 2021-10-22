package com.campusdemo.example1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerTest {
    @Autowired
    private MockMvc mvc;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MessageRepository messageRepository;

    public String getJSON(String path) throws Exception {
        Path paths = Paths.get(path);
        return new String(Files.readAllBytes(paths));
    }

    public void createTestData() throws Exception {
        String commentStr = getJSON("src/test/resources/userComments.json");
        Message message = mapper.readValue(commentStr, Message.class);
        this.messageRepository.save(message);
    }

    public void createManyTestDataCases() throws Exception {
        String commentStr = getJSON("src/test/resources/manyUserComments.json");
        TypeReference<List<Message>> messages = new TypeReference<List<Message>>() {};
        List<Message> jsonToMessageList = mapper.readValue(commentStr, messages);
        this.messageRepository.saveAll(jsonToMessageList);
    }

    @Test
    public void testIsEmpty() throws Exception {
        this.mvc.perform(get("/comments")).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateUser() throws Exception {
        String userComment = getJSON("src/test/resources/userComments.json");

        Message message = mapper.readValue(userComment, Message.class);

        RequestBuilder request = post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userComment);

        this.mvc.perform(request).andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.username", is(message.getUsername())))
                .andExpect(jsonPath("$.comment", is(message.getComment())));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetUserComments() throws Exception {
        createTestData();
        Message message = this.messageRepository.findAll().iterator().next();
        this.mvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].username", is(message.getUsername())))
                .andExpect(jsonPath("$[0].comment", is(message.getComment())));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetUserCommentsByUsername() throws Exception {
        createManyTestDataCases();
        List<Message> messagesList = (List<Message>) this.messageRepository.findAll();
        Message message = messagesList.get(0);

        RequestBuilder request = get("/comments/username")
                .param("username", message.getUsername());

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.data.username", is(message.getUsername())))
                .andExpect(jsonPath("$.data.comment", is(message.getComment())));
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteUserCommentsByUsername() throws Exception {
        createManyTestDataCases();
        List<Message> messageList = (List<Message>) this.messageRepository.findAll();
        Message message = messageList.get(0);
        Message message1 = messageList.get(1);
        System.out.println(message1.getUsername());
        System.out.println(message1.getComment());
        RequestBuilder request = delete("/comments/username")
                .param("username", message.getUsername());

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
                /*
                .andExpect(jsonPath("$[0].username", is(message1.getUsername())))
                .andExpect(jsonPath("$[0].comment", is(message1.getComment())));

                 */
        messageList = (List<Message>) this.messageRepository.findAll();
        Message newMessage = messageList.get(0);
        System.out.println(message1.getUsername());
        System.out.println(message1.getComment());
        System.out.println(newMessage.getUsername());
        System.out.println(newMessage.getComment());

    }

}
