package com.demo.configservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Dùng dữ liệu từ DB hoặc mock dữ liệu ở đây
        // Ví dụ mock một user với username 'admin' và role 'ADMIN'
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
//                    .password("{noop}admin") // {noop} chỉ ra rằng mật khẩu không mã hóa (chỉ dùng cho test)
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }

//    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
}
