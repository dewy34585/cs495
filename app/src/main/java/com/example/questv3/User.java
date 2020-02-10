package com.example.questv3;

public class User {
    String name = "No name set";
    String email = "No email set";
    String userID = "No ID set";

    String getName() {
        return this.name;
    }
    void setName(String newName) {
        this.name = newName;
    }

    String getMail() {
        return this.email;
    }
    void setMail(String newEmail) {
        this.email = newEmail;
    }

    String getUserID() {
        return this.userID;
    }
    void setUserID(String ID) {
        this.userID = ID;
    }
}
