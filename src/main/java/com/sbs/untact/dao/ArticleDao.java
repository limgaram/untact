package com.sbs.untact.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.untact.dto.Article;

@Mapper
public interface ArticleDao {

	public Article getArticle(@Param(value = "id") int id);

	public void addArticle(Map<String, Object> param);

	public void deleteArticle(@Param(value = "id") int id);

	public void modifyArticle(@Param(value = "id") int id, @Param(value = "title") String title,
			@Param(value = "body") String body);

	public List<Article> getArticles(@Param(value = "searchKeywordType") String searchKeywordType,
			@Param(value = "searchKeyword") String searchKeyword);

	public Article getForPrintArticle(@Param("id") int id);

}
