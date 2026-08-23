package com.example.bbs.config;

import java.util.NoSuchElementException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public String handleNotFound(NoSuchElementException exception, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
		return "redirect:/articles";
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDenied(AccessDeniedException exception, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
		return "redirect:/articles";
	}
}
