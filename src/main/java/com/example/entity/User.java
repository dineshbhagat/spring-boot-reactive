package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "user")
@Data
@TypeAlias("user")
public class User {
    @Id
    private String id;

    @Field("full_name")
    private String fullName;

    @Field("date_of_birth")
    private Date dateOfBirth;
}
