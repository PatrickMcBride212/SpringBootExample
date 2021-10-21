package com.campusdemo.example1;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return this.messageRepository.save(message);
    }

}
