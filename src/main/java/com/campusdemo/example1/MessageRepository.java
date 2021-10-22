package com.campusdemo.example1;

import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    Optional<Message> findByUsername(String username);
}