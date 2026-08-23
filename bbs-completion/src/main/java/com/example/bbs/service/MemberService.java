package com.example.bbs.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import com.example.bbs.entity.Member;
import com.example.bbs.form.MemberCreateForm;
import com.example.bbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Member register(MemberCreateForm form) {
		validateDuplicateMember(form.getUsername(), form.getEmail());

		Member member = new Member(
			form.getUsername(),
			passwordEncoder.encode(form.getPassword()),
			form.getName(),
			form.getEmail()
		);
		return memberRepository.save(member);
	}

	public Member getMemberByUsername(String username) {
		return memberRepository.findByUsername(username)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
	}

	private void validateDuplicateMember(String username, String email) {
		if (memberRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
		}
		if (memberRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
		}
	}
}
