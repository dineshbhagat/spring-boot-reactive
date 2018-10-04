package com.example.converter;

import com.example.dto.ArticleDto;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

public class ArticleWriteConverter implements Converter<ArticleDto, DBObject> {

    @Override
    public DBObject convert(ArticleDto source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("_id", source.getId());
        dbo.put("article_text", source.getArticleText());
        dbo.put("comments", source.getComments());
        return dbo;
    }

}
