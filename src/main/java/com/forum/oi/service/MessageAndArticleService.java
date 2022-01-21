package com.forum.oi.service;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.repos.ArticleRepo;
import com.forum.oi.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MessageAndArticleService {
    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private MessageRepo messageRepo;

    public Iterable<Article> findAllArticlesForTopic(Message topic) {
        return articleRepo.findAllArticlesByIdTopic(topic);
    }

    public Iterable<Message> findAllMessages() {
        return messageRepo.findAll();
    }

    public void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }

    public void deleteArticle(Long id) {
        articleRepo.deleteById(id);
    }

    public Article findById(Long id) {

        if (articleRepo.findById(id).isPresent()) {
            return articleRepo.findById(id).get();
        }
        return null;
    }

    public void createAndSaveArticle(String title, User author, Message topic) {

        Article article = new Article(title, author, topic);

        articleRepo.save(article);
    }

    public void saveTextArticle(String textArticle, Article article) {

        article.setTextArticle(textArticle);

        articleRepo.save(article);
    }

    public void saveChangedArticleTitle(User currentUser, String title, Article article) {

        if (article.getAuthor().equals(currentUser) || currentUser.isAdmin() || currentUser.isModerator()) {
            if (StringUtils.hasText(title)) {
                article.setTitle(title);
            }

            articleRepo.save(article);
        }
    }

    public void saveChangedArticle(User currentUser, Long idArticle, String textArticle) {

        Article currentArticle = null;

        if (findById(idArticle) != null) {
            currentArticle = findById(idArticle);
        }

        if (currentArticle != null && (currentArticle.getAuthor().equals(currentUser) || currentUser.isAdmin() || currentUser.isModerator())) {

            if (StringUtils.hasText(textArticle)) {
                currentArticle.setTextArticle(textArticle);
            }

            articleRepo.save(currentArticle);
        }
    }

    public void createAndSaveMessage(String text, User user) {

        Message message = new Message(text, user);

        messageRepo.save(message);
    }

    public void saveChangedMessage(User currentUser, String topic, Message message) {

        if (message.getAuthor().equals(currentUser) || currentUser.isAdmin() || currentUser.isModerator()) {
            if (StringUtils.hasText(topic)) {
                message.setText(topic);
            }

            messageRepo.save(message);
        }
    }
}
