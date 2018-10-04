package com.example.converter;

import com.example.dto.ArticleDto;
import com.example.dto.CommentDto;
import com.example.entity.Article;
import com.example.entity.Comment;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ArticleReadConverter implements Converter<DBObject, ArticleDto> {
    /** You need to register spring custom converter to MongoConverter
     * <url>https://docs.spring.io/spring-data/mongodb/docs/2.1.x/reference/html/#mongo.custom-converters</url>
     * @param article
     * @return
     */
    @Override
    public ArticleDto convert(DBObject article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setArticleText((String)article.get("article_text"));
        articleDto.setId((ObjectId)article.get("_id"));
        articleDto.setComments((List<CommentDto>)article.get("comments"));
        return articleDto;
    }
}
