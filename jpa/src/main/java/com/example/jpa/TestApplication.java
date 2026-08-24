package com.example.jpa;

import com.example.jpa.entity.Article;
import com.example.jpa.entity.Member;
import com.example.jpa.repository.ArticleRepository;
import com.example.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (memberRepository.count() == 0) {
            var members = List.of(
                    Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).build(),
                    Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).build(),
                    Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").age(23).build(),
                    Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).build()
            );
            memberRepository.saveAll(members);
        }

        if (articleRepository.count() == 0) {
            var member = memberRepository.findByName("윤서준").getFirst();
            for (int i = 0; i < 100; i++) {
                var article = Article.builder()
                        .title("제목 " + i)
                        .description("본문 " + i)
                        .created(LocalDateTime.now())
                        .updated(LocalDateTime.now())
                        .member(member).build();
                articleRepository.save(article);
            }
        }

        var members = memberRepository.findAll();
        for (var member : members) {
            log.info("회원 {}", member);
        }

        Pageable pageable = PageRequest.of(0, 10, Sort.by(DESC, "title"));
        var articles = articleRepository.findAll(pageable);
        for (var article : articles) {
            log.info("게시글 {}", article);
        }
    }
}
