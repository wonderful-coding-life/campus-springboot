package com.example.restful.controller;

import com.example.restful.dto.MemberRequest;
import com.example.restful.dto.MemberResponse;
import com.example.restful.entity.Member;
import com.example.restful.service.ArticleService;
import com.example.restful.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponse postMembers(@RequestBody MemberRequest memberRequest) {
        return memberService.subscribe(memberRequest);
    }

    @PostMapping("/api/v2/members")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MemberResponse> postMembersBatch(@RequestBody List<MemberRequest> memberRequests) {
        return memberService.subscribeBatch(memberRequests);
    }

    @GetMapping("/members")
    public List<MemberResponse> getMembers() {
        return memberService.findAll();
    }

    @GetMapping("/members/{id}")
    public MemberResponse getMemberById(@PathVariable("id") Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/members/{id}")
    public MemberResponse putMembers(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest) {
        return memberService.update(id, memberRequest);
    }

    @PatchMapping("/members/{id}")
    public MemberResponse patchMembers(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest) {
        return memberService.patch(id, memberRequest);
    }

    @DeleteMapping("/members/{id}")
    public void deleteMembers(@PathVariable("id") Long id) {
        memberService.deleteById(id);
    }
}
