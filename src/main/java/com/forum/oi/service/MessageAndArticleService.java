package com.forum.oi.service;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import com.forum.oi.repos.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageAndArticleService {
    @Autowired
    private ArticleRepo articleRepo;

    public Iterable<Article> findAllArticlesForTopic(Message topic) {
        return articleRepo.findAllArticlesByIdTopic(topic);
    }

    public Optional<Article> findArticleById(Long article) {
        return articleRepo.findById(article);
    }
}
