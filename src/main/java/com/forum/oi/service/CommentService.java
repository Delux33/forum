package com.forum.oi.service;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Comment;
import com.forum.oi.domain.User;
import com.forum.oi.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    public Iterable<Comment> findCommentsForArticle(Article article) {
        return commentRepo.findCommentsByArticle(article);
    }

    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    public void createAndSaveComment(String comment, User author, Article article) {

        Comment newComment = new Comment(comment, author, article);

        commentRepo.save(newComment);
    }
}
