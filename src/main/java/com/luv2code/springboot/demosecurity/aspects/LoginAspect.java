package com.luv2code.springboot.demosecurity.aspects;

import com.luv2code.springboot.demosecurity.Entity.LoginActivity;
import com.luv2code.springboot.demosecurity.Repositories.LoginActivityRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoginAspect {


    private final LoginActivityRepository loginActivityRepository;

    public LoginAspect(LoginActivityRepository loginActivityRepository) {
        this.loginActivityRepository = loginActivityRepository;
    }

    // This class will track whenever a user is logging in .
    @Before("execution(public String showHome())")
    public void trackLogIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            String username = auth.getName();
            LoginActivity activity = new LoginActivity(username, LocalDateTime.now());
            loginActivityRepository.save(activity);
        }
    }
}
