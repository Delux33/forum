package com.forum.oi.service;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import com.forum.oi.repos.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageAndArticleService {
    @Autowired
    private ArticleRepo articleRepo;

    public Iterable<Article> findAllArticlesForTopic(Message topic) {
        return articleRepo.findAllArticlesByIdTopic(topic);
    }
}
