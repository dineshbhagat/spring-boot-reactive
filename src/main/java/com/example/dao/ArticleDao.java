package com.example.dao;

import com.example.entity.Article;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends MongoRepository<Article, ObjectId> {
    @Query("{'Article.tags':?0}")
    List<Article> findByTag(String tag);
}
