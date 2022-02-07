package com.forum.oi.controller;


import com.forum.oi.domain.Comment;
import com.forum.oi.service.BotService;
import com.forum.oi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/topics/{message}/{article}")
public class BotController {

    @Autowired
    private BotService botService;

    @Autowired
    private CommentService commentService;

    //@Scheduled(cron = "0 */1 * * * *")
    public void addBotAnswer() {

        Iterable<Comment> allComments = commentService.findAllComments();

        for (Comment comment : allComments) {

            if (!comment.isAnsweredComment()) {

                String textForBot = botService.textForBot();

                String time = LocalDateTime.now().getYear() + ":" + LocalDateTime.now().getMonthValue() + ":" + LocalDateTime.now().getDayOfMonth() + " "
                        + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();

                comment.setAnswerTime(time);
                comment.setAnswer(textForBot);
                comment.setAnsweredComment(true);

                commentService.save(comment);
            }
        }
    }

    @PostMapping("delete/answer/{comment}")
    public String deleteAnswer(@PathVariable Comment comment,
                               @PathVariable Long message,
                               @PathVariable Long article) {

        comment.setAnswer(null);
        comment.setAnswerTime(null);
        comment.setAnsweredComment(false);
        commentService.save(comment);

        return "redirect:/topics/" + message + "/" + article;
    }
}
