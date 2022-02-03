package com.forum.oi.service;

import com.forum.oi.domain.TextForBot;
import com.forum.oi.repos.TextForBotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BotService {

    @Autowired
    private TextForBotRepo textForBotRepo;

    public String textForBot() {

        Long random = 1 + (long) (Math.random() * 10);

        Optional<TextForBot> textForBot = textForBotRepo.findById(random);

        if (textForBot.isPresent()) {

            if (textForBot.get().isUsed()) {

                return textForBot();

            } else {

                TextForBot text = textForBot.get();

                text.setUsed(true);
                textForBotRepo.save(text);

                return text.getText();
            }
        }
        return "Не знаю, что тебе ответить";
    }
}
