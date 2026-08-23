package com.example.bbs.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bbs.entity.Article;
import com.example.bbs.form.ArticleForm;
import com.example.bbs.service.ArticleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleService articleService;

	@GetMapping("/articles")
	public String list(
		@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
		Model model
	) {
		Page<Article> articles = articleService.getArticles(pageable);
		model.addAttribute("articles", articles);
		return "articles/list";
	}

	@GetMapping("/articles/{articleId}")
	public String detail(
		@PathVariable Long articleId,
		@AuthenticationPrincipal UserDetails userDetails,
		Model model
	) {
		Article article = articleService.getArticle(articleId);
		model.addAttribute("article", article);
		model.addAttribute("isAuthor", userDetails != null && articleService.isAuthor(article, userDetails.getUsername()));
		return "articles/detail";
	}

	@GetMapping("/articles/new")
	public String createForm(@ModelAttribute("articleForm") ArticleForm form) {
		return "articles/form";
	}

	@PostMapping("/articles")
	public String create(
		@Valid @ModelAttribute("articleForm") ArticleForm form,
		BindingResult bindingResult,
		@AuthenticationPrincipal UserDetails userDetails
	) {
		if (bindingResult.hasErrors()) {
			return "articles/form";
		}

		articleService.createArticle(form, userDetails.getUsername());
		return "redirect:/articles";
	}

	@GetMapping("/articles/{articleId}/edit")
	public String editForm(
		@PathVariable Long articleId,
		@AuthenticationPrincipal UserDetails userDetails,
		Model model
	) {
		Article article = articleService.getArticleForAuthor(articleId, userDetails.getUsername());
		ArticleForm form = new ArticleForm();
		form.setTitle(article.getTitle());
		form.setContent(article.getContent());
		model.addAttribute("articleForm", form);
		model.addAttribute("articleId", articleId);
		return "articles/form";
	}

	@PostMapping("/articles/{articleId}/edit")
	public String edit(
		@PathVariable Long articleId,
		@Valid @ModelAttribute("articleForm") ArticleForm form,
		BindingResult bindingResult,
		@AuthenticationPrincipal UserDetails userDetails,
		Model model
	) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("articleId", articleId);
			return "articles/form";
		}

		articleService.updateArticle(articleId, form, userDetails.getUsername());
		return "redirect:/articles/" + articleId;
	}

	@PostMapping("/articles/{articleId}/delete")
	public String delete(@PathVariable Long articleId, @AuthenticationPrincipal UserDetails userDetails) {
		articleService.deleteArticle(articleId, userDetails.getUsername());
		return "redirect:/articles";
	}
}
