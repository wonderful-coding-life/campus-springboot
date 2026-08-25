package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/model")
    public String getModel(Model model) {
        model.addAttribute("name", "윤광철");
        model.addAttribute("email", "KwangcheolYun@hanbit.co.kr");
        var member = Member.builder().name("윤서준").email("SeojoonYun@hanbit.co.kr").age(10).build();
        model.addAttribute("member", member);
        return "model";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        var members = List.of(Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").age(23).build(),
                Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build());
        model.addAttribute("members", members);
        return "list";
    }

    @GetMapping("/utility")
    public String getUtility(Model model) {
        //String formatted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now);
        return "utility";
    }

    @GetMapping("/link")
    public String getLink(@RequestParam(required = false) Integer id, Model model) {
        model.addAttribute("id", id);
        return "link";
    }
}
