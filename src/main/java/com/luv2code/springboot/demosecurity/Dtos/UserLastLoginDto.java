package com.luv2code.springboot.demosecurity.Dtos;

import java.time.LocalDateTime;

public interface UserLastLoginDto {
    String getUsername();
    LocalDateTime getLastLogin();
}
