package com.forum.oi.repos;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Long> {

    Iterable<Comment> findCommentsByArticle(Article article);
}
