package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Document(collection = "tag")
@Data
public class Tag {
    @Id
    @Field("tag_id")
    private String tagId;

    private String name;
    private String description;

    /**
     * One tag can be a part of multiple articles e.g. there can be multiple articles on Java tag
     */
    @DBRef
    private Set<Article> articles;
}
