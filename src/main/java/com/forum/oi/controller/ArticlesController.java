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
import java.util.Optional;

@Controller
@RequestMapping("/topics")
public class ArticlesController {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private MessageAndArticleService messageAndArticleService;

    @GetMapping("{topic}")
    public String topic(@PathVariable Message topic,
                        Map<String, Object> model) {

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(topic);

        model.put("articlesTitle", articlesTitle);
        model.put("id_message", topic.getId());

        return "articleTitle";
    }

    @PostMapping("{topic}")
    public String addTopic(@RequestParam String title,
                           @AuthenticationPrincipal User user,
                           @PathVariable Message topic,
                           Map<String, Object> model) {

        Article article = new Article(title, user, topic);

        articleRepo.save(article);

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(topic);

        model.put("articlesTitle", articlesTitle);

        return "redirect:/topics/" + topic.getId();
    }

    @GetMapping("{topic}/{article}")
    public String article(@PathVariable Message topic,
                          @PathVariable Long article,
                          Map<String, Object> model) {

        Optional<Article> articles = messageAndArticleService.findArticleById(article);

        model.put("articles", articles);
        model.put("id_message", topic.getId());
        model.put("id_article", article);

        return "article";
    }

}
