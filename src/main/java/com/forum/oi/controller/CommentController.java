package com.forum.oi.controller;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Comment;
import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/topics/{message}/{article}")
public class CommentController {

    @Autowired
    private CommentService commentService;

//    @GetMapping("/safas")
//    public String showComments(Map<String, Object> model) {
//
//        Iterable<Comment> comments = commentService.findAllComments();
//
//        model.put("comments", comments);
//
//        return "article";
//    }

    @PostMapping("comment")
    public String addComment(@RequestParam String comment,
                             @AuthenticationPrincipal User author,
                             @PathVariable Message message,
                             @PathVariable Article article) {

        Comment newComment = new Comment(comment, author, article);

        commentService.saveComment(newComment);

        return "redirect:/topics/" + message.getId() + "/" + article.getId();
    }

}
