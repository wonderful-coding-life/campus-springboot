package com.example.bbs.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bbs.form.MemberCreateForm;
import com.example.bbs.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/members/new")
	public String createForm(@ModelAttribute("memberCreateForm") MemberCreateForm form) {
		return "members/create";
	}

	@PostMapping("/members")
	public String create(@Valid @ModelAttribute("memberCreateForm") MemberCreateForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "members/create";
		}

		try {
			memberService.register(form);
		} catch (IllegalArgumentException exception) {
			bindingResult.reject("member.create", exception.getMessage());
			return "members/create";
		}

		return "redirect:/login";
	}
}
