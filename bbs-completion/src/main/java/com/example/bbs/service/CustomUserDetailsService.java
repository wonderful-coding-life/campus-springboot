package com.example.bbs.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bbs.entity.Member;

import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Member member = memberService.getMemberByUsername(username);
			return User.withUsername(member.getUsername())
				.password(member.getPassword())
				.roles("USER")
				.build();
		} catch (NoSuchElementException exception) {
			throw new UsernameNotFoundException("존재하지 않는 회원입니다.", exception);
		}
	}
}
