package com.luv2code.springboot.demosecurity.Repositories;

import com.luv2code.springboot.demosecurity.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {
    Optional<Member> findByUserId(String userId);
}
