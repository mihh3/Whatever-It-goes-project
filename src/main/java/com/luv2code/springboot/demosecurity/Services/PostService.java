package com.luv2code.springboot.demosecurity.Services;

import com.luv2code.springboot.demosecurity.Entity.Member;
import com.luv2code.springboot.demosecurity.Entity.Post;
import com.luv2code.springboot.demosecurity.Repositories.MemberRepository;
import com.luv2code.springboot.demosecurity.Repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }


    @Transactional
    public void createPost(String content, String username) {
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        Post post = new Post();
        doCreatePost(post, content, member);
        postRepository.save(post);
    }



    // Helping method .
    private void doCreatePost(Post post, String content, Member member) {
        post.setContent(content);
        post.setAuthor(member);
        post.setCreatedAt(LocalDateTime.now());

    }

    @Transactional(readOnly = true)
    public Page<Post> getFeed(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
    }
}
