package com.casestudy.rag_chat_history_service.repository;

import com.casestudy.rag_chat_history_service.entity.ChatMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatMessagesRepository extends JpaRepository<ChatMessages, UUID> {

Page<ChatMessages> findBySessionIdOrderByCreatedAtAsc(UUID sessionId, Pageable pageable);
}
