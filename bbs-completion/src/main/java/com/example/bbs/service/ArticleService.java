package com.example.bbs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bbs.entity.Article;
import com.example.bbs.entity.Member;
import com.example.bbs.form.ArticleForm;
import com.example.bbs.repository.ArticleRepository;
import com.example.bbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

	private final ArticleRepository articleRepository;
	private final MemberRepository memberRepository;

	public Page<Article> getArticles(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	public Article getArticle(Long articleId) {
		return articleRepository.findById(articleId)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));
	}

	public Article getArticleForAuthor(Long articleId, String username) {
		Article article = getArticle(articleId);
		validateAuthor(article, username);
		return article;
	}

	@Transactional
	public Article createArticle(ArticleForm form, String username) {
		Member author = getMemberByUsername(username);
		return articleRepository.save(new Article(form.getTitle(), form.getContent(), author));
	}

	@Transactional
	public void updateArticle(Long articleId, ArticleForm form, String username) {
		Article article = getArticle(articleId);
		validateAuthor(article, username);
		article.update(form.getTitle(), form.getContent());
	}

	@Transactional
	public void deleteArticle(Long articleId, String username) {
		Article article = getArticle(articleId);
		validateAuthor(article, username);
		articleRepository.delete(article);
	}

	public boolean isAuthor(Article article, String username) {
		return article.getAuthor().getUsername().equals(username);
	}

	private Member getMemberByUsername(String username) {
		return memberRepository.findByUsername(username)
			.orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다."));
	}

	private void validateAuthor(Article article, String username) {
		if (!isAuthor(article, username)) {
			throw new AccessDeniedException("게시글 작성자만 수정하거나 삭제할 수 있습니다.");
		}
	}
}
