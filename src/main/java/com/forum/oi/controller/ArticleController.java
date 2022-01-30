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
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/topics")
public class ArticleController {

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
    public String addTitle(@AuthenticationPrincipal User author,
                           @PathVariable Message topic,
                           @RequestParam("title") String article,
                           Model model) {

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(topic);

        model.addAttribute("articleTitle", articlesTitle);
        model.addAttribute("id_message", topic.getId());


        if (!StringUtils.hasText(article)) {
            model.addAttribute("titleArticleError", "Заголовок статьи не может быть пустой");
        } else {

            if (article.length() > 10) {
                model.addAttribute("titleArticleError", "Заголовок статьи больше 10 символов " +
                        "- это очень много");
                model.addAttribute("title", article);

                return "articleTitle";
            }

            if (messageAndArticleService.findByTitleArticle(article).equals(article)) {
                model.addAttribute("titleArticleError", "Заголовок статьи с таким именем " +
                        "уже существует");
                model.addAttribute("title", article);

                return "articleTitle";
            }

            messageAndArticleService.createAndSaveArticle(article, author, topic);

            return "redirect:/topics/" + topic.getId();
        }
        return "articleTitle";
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
    public String addTextArticle(@AuthenticationPrincipal User currentUser,
                                 @RequestParam String textArticle,
                                 @PathVariable Article article,
                                 @PathVariable Message topic,
                                 Model model) {

        Iterable<Comment> comments = commentService.findCommentsForArticle(article);

        model.addAttribute("comments", comments);
        model.addAttribute("isOwnerArticle", currentUser.equals(article.getAuthor()));
        model.addAttribute("id_message", topic.getId());
        model.addAttribute("article", article);

        if (textArticle.length() > 10) {
            model.addAttribute("textArticleError", "Статья больше 10 символов " +
                    "- это очень много");
            return "article";
        }

        if (!StringUtils.hasText(textArticle)) {
            model.addAttribute("textArticleError", "Статья не может быть пустой");
        } else {
            messageAndArticleService.saveTextArticle(textArticle, article);
        }
        return "article";
    }

    @GetMapping("{message}/edit/{article}")
    public String editArticleTitle(@AuthenticationPrincipal User currentUser,
                                   @PathVariable Message message,
                                   @PathVariable Article article,
                                   Model model) {

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(message);

        model.addAttribute("articleTitle", articlesTitle);
        model.addAttribute("article", article);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(message.getAuthor()));

        return "editArticleTitle";
    }

    @PostMapping("{message}/edit/{article}")
    public String saveChangedArticleTitle(@AuthenticationPrincipal User currentUser,
                                          @PathVariable Article article,
                                          @RequestParam String title,
                                          @PathVariable Message message,
                                          Model model) {

        Iterable<Article> articlesTitle = messageAndArticleService.findAllArticlesForTopic(message);

        model.addAttribute("articleTitle", articlesTitle);
        model.addAttribute("article", article);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(message.getAuthor()));

        if (!StringUtils.hasText(title)) {
            model.addAttribute("titleArticleError", "Заголовок статьи не может быть пустой");
        } else {

            if (title.length() > 10) {
                model.addAttribute("titleArticleError", "Заголовок статьи больше 10 символов " +
                        "- это очень много");
                model.addAttribute("title", title);

                return "editArticleTitle";
            }

            if (article.getTitle().equals(title)) {
                model.addAttribute("titleArticleError", "Изменений не найдено");
                model.addAttribute("title", title);

                return "editArticleTitle";
            }

            if (messageAndArticleService.findByTitleArticle(title).equals(title)) {
                model.addAttribute("titleArticleError", "Заголовок статьи с таким именем " +
                        "уже существует");
                model.addAttribute("title", title);

                return "editArticleTitle";
            }

            messageAndArticleService.saveChangedArticleTitle(currentUser, title, article);

            return "redirect:/topics/" + message.getId();
        }
        return "editArticleTitle";
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
                                     @PathVariable Article article,
                                     @RequestParam String textArticle,
                                     @PathVariable Message message,
                                     Model model) {

        model.addAttribute("id_message", message.getId());
        model.addAttribute("text", textArticle);
        model.addAttribute("article", article);
        model.addAttribute("isCurrentUser", currentUser.equals(message.getAuthor()));

        if (!StringUtils.hasText(textArticle)) {
            model.addAttribute("textArticleError", "Статья не может быть пустой");
        } else {

            if (textArticle.length() > 10) {
                model.addAttribute("textArticleError", "Статья больше 10 символов " +
                        "- это очень много");
                model.addAttribute("textArticle", textArticle);

                return "editArticle";
            }

            if (article.getTextArticle().equals(textArticle)) {
                model.addAttribute("textArticleError", "Изменений не найдено");
                model.addAttribute("textArticle", textArticle);

                return "editArticle";
            }

            messageAndArticleService.saveChangedArticle(currentUser, article.getId(), textArticle);

            return "redirect:/topics/" + message.getId() + "/" + article.getId();
        }
        return "editArticle";
    }
}
