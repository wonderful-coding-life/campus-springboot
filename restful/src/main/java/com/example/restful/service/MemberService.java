package com.example.restful.service;

import com.example.restful.dto.MemberRequest;
import com.example.restful.dto.MemberResponse;
import com.example.restful.entity.Member;
import com.example.restful.exception.MemberNotFoundException;
import com.example.restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse subscribe(MemberRequest memberRequest) {
        Member member =  Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .build();
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    @Transactional
    public List<MemberResponse> subscribeBatch(List<MemberRequest> memberRequests) {
        return memberRequests.stream().map(this::subscribe).toList();
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return mapToMemberResponse(member);
    }

    public List<MemberResponse> findAll_() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponse> result = new ArrayList<>();
        for (Member member: members) {
            MemberResponse response = mapToMemberResponse(member);
            result.add(response);
        }
        return result;
    }

    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream().map(this::mapToMemberResponse).toList();
    }

    public MemberResponse update(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());
        member.setAge(memberRequest.getAge());
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    public MemberResponse patch(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        // 전달된 값이 있는 필드만 업데이트
        if (memberRequest.getName() != null) member.setName(memberRequest.getName());
        if (memberRequest.getEmail() != null) member.setEmail(memberRequest.getEmail());
        if (memberRequest.getAge() != null) member.setAge(memberRequest.getAge());
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    public void deleteById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        memberRepository.delete(member);
    }

    private MemberResponse mapToMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }
}
