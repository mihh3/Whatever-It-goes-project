package com.luv2code.springboot.demosecurity.Repositories;

import com.luv2code.springboot.demosecurity.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
