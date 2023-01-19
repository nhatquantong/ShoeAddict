package com.example.ecommercedraft;

public class User {
    public String fullname, status, email;

    public User(){

    }

    public User(String fullname, String status, String email) {
        this.fullname = fullname;
        this.status = status;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }
}
