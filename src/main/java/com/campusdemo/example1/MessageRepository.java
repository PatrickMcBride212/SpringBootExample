package com.campusdemo.example1;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    Optional<Message> findByUsername(String username);
    @Modifying
    @Query("delete from Message m where m.username = ?1")
    void deleteByUsername(String username);

}