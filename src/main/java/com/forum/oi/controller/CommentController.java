package com.forum.oi.controller;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Comment;
import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/topics/{message}/{article}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public String addComment(@AuthenticationPrincipal User currentUser,
                             @RequestParam String comment,
                             @PathVariable Message message,
                             @PathVariable Article article,
                             Model model) {

        Iterable<Comment> comments = commentService.findCommentsForArticle(article);

        model.addAttribute("comments", comments);
        model.addAttribute("isOwnerArticle", currentUser.equals(article.getAuthor()));
        model.addAttribute("id_message", message.getId());
        model.addAttribute("article", article);

        if (comment.length() > 10) {
            model.addAttribute("commentError", "Комментарий больше 10 символов - это очень много");

            return "article";
        }

        if (!StringUtils.hasText(comment)) {
            model.addAttribute("commentError", "Комментарий не может быть пустой");
        } else {

            commentService.createAndSaveComment(comment, currentUser, article);

            return "redirect:/topics/" + message.getId() + "/" + article.getId();
        }
        return "article";
    }

    @PostMapping("delete/{comment}")
    public String deleteComment(@PathVariable Long comment,
                                @PathVariable Long message,
                                @PathVariable Long article) {

        commentService.deleteComment(comment);

        return "redirect:/topics/" + message + "/" + article;
    }
}
