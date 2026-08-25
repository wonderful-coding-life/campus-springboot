package com.example.security.config;

import com.example.security.repository.MemberRepository;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain seecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        // 홈 화면은 누구나 가능
                        .requestMatchers("/").permitAll()
                        // 상품 조회는 인증된 사용자만 가능
                        .requestMatchers("/product/list").authenticated()
                        // 상품 등록/수정/삭제 화면은 관리자만 가능
                        .requestMatchers("/product/add").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/product/edit").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/product/delete").hasAuthority("ROLE_ADMIN")
                        // 정적 리소스는 누구나 가능
                        .requestMatchers("/css/**", "/js/**", "/image/**").permitAll()
                        // 그 외 화면은 로그인한 사용자만 가능
                        .anyRequest().authenticated()
                )
                //.formLogin(Customizer.withDefaults())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()) // 로그인 페이지는 누구나 접근 가능
                //.logout(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()) // 로그아웃 요청과 성공 후 이동 URL은 누구나 접근 가능
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(MemberRepository memberRepository) {
        return username -> {
            var member = memberRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(username));
            var authorities = Arrays.stream(member.getAuthorities().split(",")).map(String::trim).toArray(String[]::new);
            return User.builder()
                    .username(member.getName())
                    .password(member.getPassword())
                    .authorities(authorities)
                    .build();
        };
    }

    // h2 console

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers("/product-list.html");
    }
}
