package com.forum.oi.repos;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepo extends CrudRepository<Article, Long> {

    @Query("from Article art where art.message = :topic")
    Iterable<Article> findAllArticlesByIdTopic(Message topic);
}
