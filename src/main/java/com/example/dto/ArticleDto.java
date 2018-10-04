package com.example.dto;

import com.example.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    private ObjectId id;
    private String articleText;
    private List<CommentDto> comments;
}
