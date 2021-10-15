package com.campusdemo.example1;

import lombok.Data;


public class Message {

    private String username;
    private String comment;

    public Message(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }

    public Message() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
