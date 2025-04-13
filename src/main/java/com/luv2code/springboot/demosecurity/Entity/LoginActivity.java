package com.luv2code.springboot.demosecurity.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_activity")
public class LoginActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loggId;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "login_time" , nullable = false)
    private LocalDateTime loginTime ;

    public LoginActivity() {
    }

    public LoginActivity(String username, LocalDateTime loginTime) {
        this.username = username;
        this.loginTime = loginTime;
    }

    public Long getLoggId() {
        return loggId;
    }

    public void setLoggId(Long loggId) {
        this.loggId = loggId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }
}
