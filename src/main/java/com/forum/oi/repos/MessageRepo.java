package com.forum.oi.repos;

import com.forum.oi.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

    Message findByText(String topic);
}
