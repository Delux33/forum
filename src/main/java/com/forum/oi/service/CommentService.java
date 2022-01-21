package com.forum.oi.service;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Comment;
import com.forum.oi.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    public void saveComment(Comment comment) {
        commentRepo.save(comment);
    }

    public Iterable<Comment> findCommentsForArticle(Article article) {
        return commentRepo.findCommentsByArticle(article);
    }

    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }

}
