package com.example.restful;

import com.example.restful.entity.Member;
import com.example.restful.repository.ArticleRepository;
import com.example.restful.repository.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void beforeEach() {
        articleRepository.deleteAll();
        memberRepository.deleteAll();
        List<Member> members = List.of(
                Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                Member.builder().name("윤광철").email("Kwangcheol@hanbit.co.kr").age(43).build()
        );
        memberRepository.saveAll(members);
    }

    @AfterEach
    public void afterEach() {
        //
    }

    @Test
    @DisplayName("회원 숫자 조회")
    public void testCount() {
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2L);
    }

    @RepeatedTest(3)
    @DisplayName("전체 회원 조회")
    public void testFindAll() {
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2L);
    }

    @Test
    @Disabled("임시로 이메일 조회 테스트는 스킵하기로 함")
    @DisplayName("이메일 조회")
    public void testFindByEmail() {
        Member member = memberRepository.findByEmail("SeojunYoon@hanbit.co.kr").orElse(null);
        assertThat(member).isNotNull();
    }
}
