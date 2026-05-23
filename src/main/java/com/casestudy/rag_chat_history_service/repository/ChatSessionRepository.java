package com.casestudy.rag_chat_history_service.repository;

import com.casestudy.rag_chat_history_service.entity.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, UUID> {

List<ChatSession> findByUserIdOrderByUpdatedAtDesc(String userId);
}
