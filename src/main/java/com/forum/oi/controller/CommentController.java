package com.forum.oi.controller;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/topics/{message}/{article}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public String addComment(@RequestParam String comment,
                             @AuthenticationPrincipal User author,
                             @PathVariable Message message,
                             @PathVariable Article article) {

        commentService.createAndSaveComment(comment, author, article);

        return "redirect:/topics/" + message.getId() + "/" + article.getId();
    }

    @PostMapping("delete/{comment}")
    public String deleteComment(@PathVariable Long comment,
                                @PathVariable Long message,
                                @PathVariable Long article) {

        commentService.deleteComment(comment);

        return "redirect:/topics/" + message + "/" + article;
    }
}
