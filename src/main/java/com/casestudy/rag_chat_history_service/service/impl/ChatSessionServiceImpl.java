package com.casestudy.rag_chat_history_service.service.impl;

import com.casestudy.rag_chat_history_service.dto.request.CreateSessionRequest;
import com.casestudy.rag_chat_history_service.dto.response.ChatSessionResponse;
import com.casestudy.rag_chat_history_service.entity.ChatSession;
import com.casestudy.rag_chat_history_service.exception.ResourceNotFoundException;
import com.casestudy.rag_chat_history_service.mapper.ChatMapper;
import com.casestudy.rag_chat_history_service.repository.ChatSessionRepository;
import com.casestudy.rag_chat_history_service.service.ChatSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatSessionServiceImpl implements ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;

    private final ChatMapper chatMapper;

    @Override
    public ChatSessionResponse createChatSession(CreateSessionRequest createSessionRequest)
    {
        log.info("Creating Chat session for userId: {}, title: {}",createSessionRequest.userId(),createSessionRequest.title());

        ChatSession session=ChatSession.builder()
                .userId(createSessionRequest.userId())
                .title(createSessionRequest.title())
                .favorite(false)
                .build();

        ChatSession savedSession=chatSessionRepository.save(session);

        log.info("Chat Session created successfully with id: {}",savedSession.getId());

        return chatMapper.toSessionResponse(savedSession);
    }

    @Override
    public List<ChatSessionResponse> getChatSessions(String userId)
    {

        log.info("fetching Session for UserId: {} ",userId);

            List<ChatSessionResponse> chatSessions= chatSessionRepository.findByUserIdOrderByUpdatedAtDesc(userId)
                    .stream()
                    .map(chatMapper :: toSessionResponse)
                    .toList();

            log.info("Total Sessions fetched successfully for UserId {} : {} ",userId,chatSessions.size());

            return chatSessions;
    }

    @Override
    public ChatSessionResponse markAsFavorite(UUID sessionId)
    {
        log.info("Mark Session as favorite for sessionId: {} ",sessionId);

        ChatSession session= chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session Not Found"));

        session.setFavorite(!session.getFavorite());

        ChatSession updatedSession=chatSessionRepository.save(session);

        log.info("Status of Favorite updated Successfully for sessionId: {} ",sessionId);

        return chatMapper.toSessionResponse(updatedSession);
    }

    @Override
    public ChatSessionResponse renameSession(UUID sessionId, String title)
    {
        log.info("Rename sessionId: {} with new title: {} ",sessionId,title);

        ChatSession chatSession=chatSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session Not Found"));

        chatSession.setTitle(title);

       ChatSession updatedSession=chatSessionRepository.save(chatSession);

       log.info("Session renamed successfully for sessionId: {} ",sessionId);

        return chatMapper.toSessionResponse(updatedSession);
    }

    @Override
    public void deleteSession(UUID sessionId)
    {
        log.info("Deleting session for sessionid: {} ",sessionId);
        if(!chatSessionRepository.existsById(sessionId))
        {
            throw  new ResourceNotFoundException("Session Not found");
        }

        chatSessionRepository.deleteById(sessionId);

        log.info("Session deleted successfully with id: {} ",sessionId);
    }
}
