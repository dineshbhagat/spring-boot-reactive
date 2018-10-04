package com.example.service.impl;

import com.example.converter.ArticleReadConverter;
import com.example.dao.ArticleDao;
import com.example.dao.CommentDao;
import com.example.dao.UserDao;
import com.example.dto.ArticleDto;
import com.example.entity.Article;
import com.example.service.ArticleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private UserDao userDao;

    /**
     * No need for autowire if you are adding as constructor dependency
     *
     * @param articleDao
     */
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public Article addArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setArticleText(articleDto.getArticleText());
        articleDao.save(article);
        return article;
    }

    @Override
    public Article addArticle(Article article) {
        article.setComments(article.getComments().stream().map(comment -> {
            comment.setPostedDate(new Date());
            comment.getUser().setDateOfBirth(new Date());
            comment.getUser().setFullName(comment.getUser().getFullName());
            comment.setUser(userDao.save(comment.getUser()));
            comment.setId(commentDao.save(comment).getId());
            return comment;
        }).collect(Collectors.toList()));
        articleDao.save(article);
        return article;
    }

    @Override
    public Article getArticle(String articleId) throws Exception {
        Optional<Article> article = articleDao.findById(new ObjectId(articleId));
        if (article.isPresent()) {
            return article.get();
//            return ArticleReadConverter.class(article.get());
        }
        throw new Exception("Article not found!");
    }
}
