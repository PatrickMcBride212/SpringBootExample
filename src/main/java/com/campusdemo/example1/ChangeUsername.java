package com.campusdemo.example1;

public class ChangeUsername {

    private String oldUsername;
    private String newUsername;

    public ChangeUsername(String oldUsername, String newUsername) {
        this.oldUsername = oldUsername;
        this.newUsername = newUsername;
    }

    public ChangeUsername() {
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    @Override
    public String toString() {
        return "ChangeUsername{" +
                "oldUsername='" + oldUsername + '\'' +
                ", newUsername='" + newUsername + '\'' +
                '}';
    }
}
