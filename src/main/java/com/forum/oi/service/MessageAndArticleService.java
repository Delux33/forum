package com.forum.oi.service;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import com.forum.oi.repos.ArticleRepo;
import com.forum.oi.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageAndArticleService {
    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private MessageRepo messageRepo;

    public Iterable<Article> findAllArticlesForTopic(Message topic) {
        return articleRepo.findAllArticlesByIdTopic(topic);
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
}
