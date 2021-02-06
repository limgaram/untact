package com.sbs.untact.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.ArticleService;

@Controller
public class UsrArticleController {

//ctrl + shift + r = 관련된 거 한번에 바꾸기
//(ctrl + alt + q) + p

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList(String searchKeywordType, String searchKeyword) {

		if (searchKeywordType != null) {
			// searchKeywordType이 비어있지 않으면 공백 제거
			searchKeywordType = searchKeywordType.trim();
		}

		if (searchKeywordType == null || searchKeywordType.length() == 0) {
			// searchKeywordType이 null이거나 길이가 0이면
			// 제목과 내용 둘 다에서 검색하는 걸로 설정
			searchKeywordType = "titleAndBody";
		}

		if (searchKeyword != null && searchKeyword.length() == 0) {
			// searchKeyword가 비어있지 않은데 길이가 0이면
			// searchKeyword는 null임
			searchKeyword = null;
		}

		if (searchKeyword != null) {
			// searchKeyword에 키워드가 들어오면 공백제거
			searchKeyword = searchKeyword.trim();
		}

		if (searchKeyword == null) {
			//검색어가 없을 때는 검색어타입도 null
			searchKeywordType = null;
		}

		return articleService.getArticles(searchKeywordType, searchKeyword);
	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {

		Article article = articleService.getArticle(id);
		return article;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param) {

		if (param.get("title") == null) {
			return new ResultData("F-1", "title을 입력해주세요. ");
		}

		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요. ");
		}

		return articleService.addArticle(param);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다. ");
		}

		return articleService.deleteArticle(id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(Integer id, String title, String body) {

		// int는 null 불가능, Integer은 null 가능.
		// @RequestParam(defaultValue = "0") int id
		// = 기본값으로 0이 자동으로 들어가게 설정하는 거.

		// 입력 데이터 유효셩 체크

		if (id == null) {
			return new ResultData("F-1", "id을 입력해주세요. ");
		}

		if (title == null) {
			return new ResultData("F-1", "title을 입력해주세요. ");
		}

		if (body == null) {
			return new ResultData("F-1", "body를 입력해주세요. ");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다. ");
		}

		return articleService.modifyArticle(id, title, body);

	}

}
