package com.luv2code.springboot.demosecurity.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="roles")
@IdClass(RoleId.class)
public class Role {
    @Id
    private String userId;

    @Id
    private String role;

    // Constructors, getters and setters
    public Role() {
        // Μπορεί να είναι κενός ή να περιέχει λογική αν χρειάζεται
    }

    public Role(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}


