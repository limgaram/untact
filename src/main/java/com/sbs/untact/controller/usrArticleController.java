package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class usrArticleController {
	private List<Article> articles;

	private int articlesLastId;

	public usrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();

		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "제목2", "내용2"));
	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articles.get(id - 1);
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String regDate, String title, String body) {
		articles.add(new Article(++articlesLastId, regDate, title, body));

		Map<String, Object> rs = new HashMap<>();
		rs.put("rs.resultCode", "S-1");
		rs.put("msg", "성공하였습니다.");
		rs.put("id", articlesLastId);

		return rs;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		boolean deleteArticleRs = deleteArticle(id);

		Map<String, Object> rs = new HashMap<>();

		if (deleteArticleRs) {
			rs.put("resultCode", "S-1");
			rs.put("msg", "성공하였습니다.");
		} else {
			rs.put("resultCode", "F-1");
			rs.put("msg", "해당 게시물은 존재하지 않습니다.");
		}

		rs.put("id", id);

		return rs;
	}

	private boolean deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}
}
