package com.forum.oi.controller;

import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.service.MessageAndArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TopicController {

    @Autowired
    private MessageAndArticleService messageAndArticleService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/topics")
    public String topic(Map<String, Object> model) {

        Iterable<Message> messages = messageAndArticleService.findAllMessages();

        model.put("messages", messages);

        return "topic";
    }

    @PostMapping("/topics")
    public String addTopic(@AuthenticationPrincipal User user,
                           @RequestParam String text,
                           Map<String, Object> model) {

        messageAndArticleService.createAndSaveMessage(text, user);

        Iterable<Message> messages = messageAndArticleService.findAllMessages();

        model.put("messages", messages);

        return "topic";
    }

    @GetMapping("/topics/edit/{message}")
    public String editTopic(@AuthenticationPrincipal User currentUser,
                            @PathVariable Message message,
                            Map<String, Object> model) {

        Iterable<Message> messages = messageAndArticleService.findAllMessages();

        model.put("messages", messages);
        model.put("message", message);
        model.put("isCurrentUser", currentUser.equals(message.getAuthor()));

        return "editTopic";
    }

    @PostMapping("/topics/edit/{messageId}")
    public String saveChangedMessage(@AuthenticationPrincipal User currentUser,
                                   @RequestParam Message message,
                                   @RequestParam String topic) {

        messageAndArticleService.saveChangedMessage(currentUser, topic, message);

        return "redirect:/topics";
    }

    @PostMapping("/topics/delete/{messageId}")
    public String deleteTopic(@PathVariable Long messageId) {

        messageAndArticleService.deleteMessage(messageId);

        return "redirect:/topics";
    }
}
