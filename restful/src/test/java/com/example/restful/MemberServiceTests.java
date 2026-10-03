package com.example.restful;

import com.example.restful.dto.MemberRequest;
import com.example.restful.repository.MemberRepository;
import com.example.restful.service.MemberService;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository.deleteAll();
    }

    @Test
    public void testSubscribe() {
        var memberRequest = MemberRequest.builder().name("test1").email("test1@test.com").age(10).build();
        memberService.subscribe(memberRequest);
        assertThat(memberRepository.findByEmail("test1@test.com")).isPresent();
    }

    @Test
    public void testSubscribeBatch() {
        var memberRequests = List.of(
                MemberRequest.builder().name("test1").email("test1@test.com").age(10).build(),
                MemberRequest.builder().name("test2").email("test2@test.com").age(10).build(),
                MemberRequest.builder().name("test3").email("test1@test.com").age(10).build()
        );
        assertThatThrownBy(() -> memberService.subscribeBatch(memberRequests)).isInstanceOf(RuntimeException.class);
        assertThat(memberRepository.findByEmail("test1@test.com")).isEmpty();
        assertThat(memberRepository.findByEmail("test2@test.com")).isEmpty();
        assertThat(memberRepository.findByEmail("test3@test.com")).isEmpty();
    }
}
