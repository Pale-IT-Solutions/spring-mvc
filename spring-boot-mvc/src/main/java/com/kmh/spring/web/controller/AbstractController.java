package com.kmh.spring.web.controller;

public class AbstractController {

	private final String REDIRECT = "redirect:";
	private final String COMPANY = "/company";

	private String createPath(String... str) {
		StringBuilder sb = new StringBuilder(REDIRECT);
		for (String s : str) {
			sb.append(s);
		}
		return sb.toString();
	}

	protected String comRedirect(String url) {
		return this.createPath(COMPANY, url);
	}
}