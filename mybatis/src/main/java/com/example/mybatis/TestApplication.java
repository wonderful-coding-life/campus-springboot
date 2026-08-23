package com.example.mybatis;

import com.example.mybatis.mapper.ArticleMapper;
import com.example.mybatis.mapper.MemberMapper;
import com.example.mybatis.model.Article;
import com.example.mybatis.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestApplication implements ApplicationRunner {
    private final MemberMapper memberMapper;
    private final ArticleMapper articleMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int count = memberMapper.selectAllCount();
        log.info("Member count: {}", count);

        Member member = memberMapper
                .selectByEmail("SeojunYoon@hanbit.co.kr")
                .orElseThrow();
        log.info("Member: {}", member);

        Article article = Article.builder()
                .title("Hello, MyBatis")
                .description("MyBatis is an SQL Mapper framework")
//                .created(LocalDateTime.now())
//                .updated(LocalDateTime.now())
                .memberId(member.getId())
                .build();
        int inserted = articleMapper.insert(article);
        log.info("Inserted: rowCount {}", inserted);
        log.info("Inserted: {}", article);
    }
}
