package com.forum.oi.repos;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepo extends CrudRepository<Article, Long> {

    Iterable<Article> findArticlesByMessage(Message topic);
}
