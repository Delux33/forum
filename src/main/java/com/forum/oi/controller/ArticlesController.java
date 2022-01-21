package com.forum.oi.controller;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Comment;
import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.service.CommentService;
import com.forum.oi.service.MessageAndArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/topics")
public class ArticlesController {

    @Autowired
    private MessageAndArticleService messageAndArticleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("{topic}")
    public String articles(@PathVariable Message topic,
                           Map<String, Object> model) {

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(topic);

        model.put("articleTitle", articlesTitle);
        model.put("id_message", topic.getId());

        return "articleTitle";
    }

    @PostMapping("{topic}")
    public String addTitle(@RequestParam String title,
                           @AuthenticationPrincipal User author,
                           @PathVariable Message topic,
                           Map<String, Object> model) {

        messageAndArticleService.createAndSaveArticle(title, author, topic);

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(topic);

        model.put("articlesTitle", articlesTitle);

        return "redirect:/topics/" + topic.getId();
    }

    @GetMapping("{topic}/{article}")
    public String showTextArticle(@AuthenticationPrincipal User currentUser,
                                  @PathVariable Article article,
                                  @PathVariable Message topic,
                                  Map<String, Object> model) {

        Iterable<Comment> comments = commentService.findCommentsForArticle(article);

        model.put("comments", comments);
        model.put("isOwnerArticle", currentUser.equals(article.getAuthor()));
        model.put("id_message", topic.getId());
        model.put("article", article);

        return "article";
    }

    @PostMapping("{topic}/{article}")
    public String addTextArticle(@RequestParam String textArticle,
                                 @PathVariable Article article,
                                 @PathVariable Message topic,
                                 Map<String, Object> model) {

        messageAndArticleService.saveTextArticle(textArticle, article);

        model.put("id_message", topic.getId());
        model.put("article", article);

        return "redirect:/topics/" + topic.getId() + "/" + article.getId();
    }

    @GetMapping("{message}/edit/{article}")
    public String editArticleTitle(@AuthenticationPrincipal User currentUser,
                                   @PathVariable Message message,
                                   @PathVariable Article article,
                                   Map<String, Object> model) {

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(message);

        model.put("articleTitle", articlesTitle);
        model.put("article", article);
        model.put("message", message);
        model.put("isCurrentUser", currentUser.equals(message.getAuthor()));

        return "editArticleTitle";
    }

    @PostMapping("{message}/edit/{article}")
    public String saveChangedArticleTitle(@AuthenticationPrincipal User currentUser,
                                          @RequestParam Article article,
                                          @RequestParam String title,
                                          @PathVariable Long message) {

        messageAndArticleService.saveChangedArticleTitle(currentUser, title, article);

        return "redirect:/topics/" + message;
    }

    @PostMapping("{message}/delete/{article}")
    public String deleteArticleTitle(@PathVariable Long message,
                                     @PathVariable Long article) {

        messageAndArticleService.deleteArticle(article);

        return "redirect:/topics/" + message;
    }

    @GetMapping("{message}/{article}/article")
    public String editArticle(@AuthenticationPrincipal User currentUser,
                              @PathVariable Message message,
                              @PathVariable Article article,
                              Map<String, Object> model) {

        model.put("id_message", message.getId());
        model.put("text", article.getTextArticle());
        model.put("article", article);
        model.put("isCurrentUser", currentUser.equals(message.getAuthor()));

        return "editArticle";
    }

    @PostMapping("{message}/{article}/article")
    public String saveChangedArticle(@AuthenticationPrincipal User currentUser,
                                     @RequestParam Long idArticle,
                                     @RequestParam String textArticle,
                                     @PathVariable Long message) {

        messageAndArticleService.saveChangedArticle(currentUser, idArticle, textArticle);

        return "redirect:/topics/" + message + "/" + idArticle;
    }
}
