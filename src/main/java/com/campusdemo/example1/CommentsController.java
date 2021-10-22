package com.campusdemo.example1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private MessageRepository messageRepository;

    public CommentsController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("")
    public Iterable<Message> getMessages() {
        return this.messageRepository.findAll();
    }

    @PostMapping("")
    public Message createMessage(@RequestBody Message message) {
        return this.messageRepository.save(message);
    }

    @GetMapping("/username")
    public CommentDetailResponse getUserCommentByUsername(@RequestParam String username) {
        Optional<Message> message = this.messageRepository.findByUsername(username);
        if (message.isPresent()) {
            return new CommentDetailResponse(HttpStatus.FOUND.value(), "Username successfully found.", message.get());
        }
        return new CommentDetailResponse(HttpStatus.NOT_FOUND.value(), "Username not found.", null);
    }
}
