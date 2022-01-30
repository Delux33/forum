package com.forum.oi.controller;

import com.forum.oi.domain.Message;
import com.forum.oi.domain.User;
import com.forum.oi.service.MessageAndArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
    public String addTopic(@AuthenticationPrincipal User author,
                           @RequestParam("text") String message,
                           Model model) {

        Iterable<Message> messages = messageAndArticleService.findAllMessages();

        model.addAttribute("messages", messages);

        if (!StringUtils.hasText(message)) {
            model.addAttribute("topicError", "Заголовок статьи не может быть пустой");
        } else {
            if (message.length() > 10) {
                model.addAttribute("topicError", "Заголовок статьи больше 10 символов " +
                        "- это очень много");
                model.addAttribute("text", message);

                return "topic";
            }

            if (messageAndArticleService.findByTopic(message).equals(message)) {
                model.addAttribute("topicError", "Тема с таким именем уже существует");
                model.addAttribute("text", message);

                return "topic";
            }

            messageAndArticleService.createAndSaveMessage(message, author);

            return "redirect:/topics";
        }

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
                                   @PathVariable Message messageId,
                                   @RequestParam String topic,
                                   Model model) {

        Iterable<Message> messages = messageAndArticleService.findAllMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", messageId);
        model.addAttribute("isCurrentUser", currentUser.equals(messageId.getAuthor()));

        if (!StringUtils.hasText(topic)) {
            model.addAttribute("topicError", "Заголовок статьи не может быть пустой");
        } else {

            if (topic.length() > 10) {
                model.addAttribute("topicError", "Заголовок статьи больше 10 символов " +
                        "- это очень много");
                model.addAttribute("text", topic);

                return "editTopic";
            }

            if (messageId.getText().equals(topic)) {
                model.addAttribute("topicError", "Изменений не найдено");
                model.addAttribute("text", topic);

                return "editTopic";
            }

            if (messageAndArticleService.findByTopic(topic).equals(topic)) {
                model.addAttribute("topicError", "Тема с таким именем уже существует");
                model.addAttribute("text", topic);

                return "editTopic";
            }

            messageAndArticleService.saveChangedMessage(currentUser, topic, messageId);

            return "redirect:/topics";
        }

        return "editTopic";
    }

    @PostMapping("/topics/delete/{messageId}")
    public String deleteTopic(@PathVariable Long messageId) {

        messageAndArticleService.deleteMessage(messageId);

        return "redirect:/topics";
    }
}
