package com.forum.oi.controller;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.repos.ArticleRepo;
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
    private ArticleRepo articleRepo;

    @Autowired
    private MessageAndArticleService messageAndArticleService;

    @GetMapping("{topic}")
    public String articles(@PathVariable Message topic,
                           Map<String, Object> model) {

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(topic);

        model.put("articlesTitle", articlesTitle);
        model.put("id_message", topic.getId());

        return "articleTitle";
    }

    @PostMapping("{topic}")
    public String addTitle(@RequestParam String title,
                           @AuthenticationPrincipal User author,
                           @PathVariable Message topic,
                           Map<String, Object> model) {

        Article article = new Article(title, author, topic);

        articleRepo.save(article);

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(topic);

        model.put("articlesTitle", articlesTitle);

        return "redirect:/topics/" + topic.getId();
    }

    @GetMapping("{topic}/{article}")
    public String showTextArticle(@PathVariable Article article,
                                  @PathVariable Message topic,
                                  Map<String, Object> model) {

        model.put("id_message", topic.getId());
        model.put("article", article);

        return "article";
    }

    @PostMapping("{topic}/{articleId}")
    public String addTextArticle(@RequestParam String textArticle,
                                 @PathVariable Article articleId,
                                 @PathVariable Message topic,
                                 @AuthenticationPrincipal User author,
                                 Map<String, Object> model) {

        articleId.setTextArticle(textArticle);
        articleId.setAuthor(author);

        articleRepo.save(articleId);

        model.put("id_message", topic.getId());
        model.put("article", articleId);

        return "article";
    }
}
