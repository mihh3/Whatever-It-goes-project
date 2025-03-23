package com.luv2code.springboot.demosecurity.Services;

import com.luv2code.springboot.demosecurity.Dtos.UserRegistrationDto;
import com.luv2code.springboot.demosecurity.Entity.Member;
import com.luv2code.springboot.demosecurity.Entity.Role;
import com.luv2code.springboot.demosecurity.Repositories.MemberRepository;
import com.luv2code.springboot.demosecurity.Repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final MemberRepository memberRepository ;
    private final RoleRepository roleRepository;
    //private final PasswordEncoder passwordEncoder;


    public UserService(MemberRepository memberRepository, RoleRepository roleRepository) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void save(UserRegistrationDto userDto) {
        Member user = new Member();
        user.setUserId(userDto.getUsername());
        user.setPassword(userDto.getPassword()); // 🔥 BCrypt Password
        user.setActive(true);
        memberRepository.save(user);

        Role role = new Role(userDto.getUsername(), userDto.getRole());
        assignRoles(user, userDto.getRole()); // Ή "ROLE_ADMIN"

    }

    public void assignRoles(Member member, String role) {

        Set<String> roles = new HashSet<>();
        if (role.equals("ROLE_ADMIN")) {
            roles.add("ROLE_EMPLOYEE");
            roles.add("ROLE_MANAGER");
            roles.add("ROLE_ADMIN");
        } else if (role.equals("ROLE_MANAGER")) {
            roles.add("ROLE_EMPLOYEE");
            roles.add("ROLE_MANAGER");
        } else {
            roles.add("ROLE_EMPLOYEE");
        }

        for (String r : roles) {
            roleRepository.save(new Role(member.getUserId(), r)); // Ή όποια μέθοδο χρησιμοποιείς
        }
    }

}
