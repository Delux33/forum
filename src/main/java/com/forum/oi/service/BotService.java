package com.forum.oi.service;

import com.forum.oi.domain.TextForBot;
import com.forum.oi.repos.TextForBotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotService {

    @Autowired
    private TextForBotRepo textForBotRepo;

    public String textForBot() {

        List<TextForBot> textForBotHasFalse = textForBotRepo.findByUsedIsFalse();

        if (textForBotHasFalse.isEmpty()) {

            return "Не знаю, что тебе ответить";
        } else {

            int random = textForBotHasFalse.size() == 1 ? 0 : (1 + (int) (Math.random() * (textForBotHasFalse.size() - 1)));

            TextForBot text = textForBotHasFalse.get(random);

            if (text.getText().isEmpty()) {

                if (textForBotHasFalse.size() == 1) {

                    return "Не знаю, что тебе ответить";
                } else {

                    while (text.getText().isEmpty()) {

                        text = textForBotHasFalse.get(++random);
                    }
                }

            } else {

                text.setUsed(true);
                textForBotRepo.save(text);

                return text.getText();
            }
        }
        return "Не знаю, что тебе ответить";
    }
}
