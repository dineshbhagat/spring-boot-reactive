package com.example.service;

import com.example.dto.ArticleDto;
import com.example.entity.Article;

public interface ArticleService {
    Article addArticle(ArticleDto articleDto);

    Article addArticle(Article article);

    Article getArticle(String articleId) throws Exception;
}
