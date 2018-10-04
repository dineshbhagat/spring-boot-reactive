package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "comment")
@Data
/**
 * @TypeAlias replaces the full path name with a value of our choice.
 * e.g. instead of showing
 * Comment:{
 *    "id":"",
 *    "str":"",
 *    "user": {
 *        _class : "com.example.entity.User"
 *    }
 * }
 * it will show as :
 * Comment:{
 *    "id":"",
 *    "str":"",
 *    "user": {
 *        _class : "user"
 *    }
 * }
 */
@TypeAlias("comment")
public class Comment implements Serializable {

    @Id
    private String id;

    private String str;

    @Field("posted_date")
    private Date postedDate;

    @DBRef
    private User user;
}
