package com.forum.oi.service;

import com.forum.oi.domain.Article;
import com.forum.oi.domain.Comment;
import com.forum.oi.domain.User;
import com.forum.oi.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    public Iterable<Comment> findCommentsForArticle(Article article) {
        return commentRepo.findCommentsByArticle(article);
    }

    public Iterable<Comment> findAllComments() {
        return commentRepo.findAll();
    }

    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    public void createAndSaveComment(String comment, User author, Article article) {

        String time = LocalDateTime.now().getYear() + ":" + LocalDateTime.now().getMonthValue() + ":" + LocalDateTime.now().getDayOfMonth() + " "
                + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();

        Comment newComment = new Comment(comment, author, article, time);

        commentRepo.save(newComment);
    }

    public void save(Comment comment) {
        commentRepo.save(comment);
    }
}
