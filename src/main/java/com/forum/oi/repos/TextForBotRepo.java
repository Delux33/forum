package com.forum.oi.repos;

import com.forum.oi.domain.TextForBot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TextForBotRepo extends CrudRepository<TextForBot, Long>  {

    List<TextForBot> findByUsedIsFalse();
}
