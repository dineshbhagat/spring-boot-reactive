package com.example.controller;


import com.example.converter.CommentConverter;
import com.example.dto.ArticleDto;
import com.example.dto.CommentDto;
import com.example.dto.UserDto;
import com.example.entity.Article;
import com.example.entity.Comment;
import com.example.entity.User;
import com.example.service.ArticleService;
import com.example.service.CommentService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleCommentController {

    private UserService userService;

    @Autowired
    private CommentService commentService;

    private ArticleService articleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ArticleCommentController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @RequestMapping(value = "/health_check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getDemo() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "Hello World!");
        return map;
    }

    /**
     * ideally you should not return article's id as response"
     *
     * @param articleDto
     * @return
     */
    @PostMapping(value = "/article", produces = MediaType.APPLICATION_JSON_VALUE)
    public Article addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PostMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Comment addComment(@RequestBody CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

    @GetMapping(value = "/comment/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentDto addComment(@PathVariable Long commentId) throws Exception {
        return CommentConverter.getDto(commentService.getComment(commentId));
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String userId) throws Exception {
        return userService.getUser(userId);
    }

    @GetMapping(value = "/article/{articleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Article getArticle(@PathVariable String articleId) throws Exception {
        return articleService.getArticle(articleId);
    }

}

