package com.luv2code.springboot.demosecurity.Repositories;

import com.luv2code.springboot.demosecurity.Entity.LoginActivity;
import com.luv2code.springboot.demosecurity.Dtos.UserLastLoginDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LoginActivityRepository extends JpaRepository<LoginActivity,Long> {

    @Query("SELECT la.username AS username, MAX(la.loginTime) AS lastLogin FROM LoginActivity la GROUP BY la.username")
    List<UserLastLoginDto> findLastLoginTimes();
}
