package com.luv2code.springboot.demosecurity.Repositories;

import com.luv2code.springboot.demosecurity.Entity.LoginActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginActivityRepository extends JpaRepository<LoginActivity,Long> {
}
