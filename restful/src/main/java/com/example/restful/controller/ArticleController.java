package com.example.restful.controller;

import com.example.restful.dto.ArticleRequest;
import com.example.restful.dto.ArticleResponse;
import com.example.restful.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/articles")
    public ArticleResponse postArticles(@RequestParam("memberId") Long memberId, @RequestBody ArticleRequest articleRequest) {
        return articleService.create(memberId, articleRequest);
    }

    @GetMapping("/articles/{id}")
    public ArticleResponse getArticles(@PathVariable("id") Long id) {
        return articleService.findById(id);
    }

    @PutMapping("/articles/{id}")
    public ArticleResponse putArticlesById(@PathVariable("id") Long id, @RequestBody ArticleRequest articleRequest) {
        return articleService.update(id, articleRequest);
    }

    @GetMapping("/articles")
    public Page<ArticleResponse> getArticles(@PageableDefault(page=0, size=5, sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
        return articleService.findAll(pageable);
    }
}
