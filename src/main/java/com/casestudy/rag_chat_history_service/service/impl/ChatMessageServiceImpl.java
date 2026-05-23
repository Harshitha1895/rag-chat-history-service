package com.casestudy.rag_chat_history_service.service.impl;

import com.casestudy.rag_chat_history_service.dto.request.AddMessageRequest;
import com.casestudy.rag_chat_history_service.dto.response.ChatMessageResponse;
import com.casestudy.rag_chat_history_service.entity.ChatMessages;
import com.casestudy.rag_chat_history_service.entity.ChatSession;
import com.casestudy.rag_chat_history_service.exception.ResourceNotFoundException;
import com.casestudy.rag_chat_history_service.mapper.ChatMapper;
import com.casestudy.rag_chat_history_service.repository.ChatMessagesRepository;
import com.casestudy.rag_chat_history_service.repository.ChatSessionRepository;
import com.casestudy.rag_chat_history_service.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatSessionRepository chatSessionRepository;
    private final ChatMessagesRepository chatMessagesRepository;
    private final ChatMapper chatMapper;


    @Override
    public ChatMessageResponse addMessage(UUID sessionId, AddMessageRequest request) {

        log.info("Adding Message to sessionId :{} ",sessionId  );

       ChatSession chatSession= chatSessionRepository.findById(sessionId)
                .orElseThrow(() ->{
                    log.error("Session not found with id: {} ",sessionId);

                    return new ResourceNotFoundException("Session Not Found");
                } );

        ChatMessages chatMessages=ChatMessages.builder()
                .session(chatSession)
                .sender(request.sender())
                .content(request.content())
                .retrievedContext(request.retrievedContext())
                .build();

        ChatMessages savedMessages=chatMessagesRepository.save(chatMessages);

        log.info("Message added successfully with id : {} to sessionId: {} ",savedMessages.getId(),sessionId);

        return chatMapper.toMessageResponse(savedMessages);

    }

    @Override
    public Page<ChatMessageResponse> getMessage(UUID sessionId, int page, int size) {

        log.info("Fetching messages for sessionId: {} ",sessionId);

        Page<ChatMessageResponse> response= chatMessagesRepository.findBySessionIdOrderByCreatedAtAsc(sessionId, PageRequest.of(page,size))
                .map(chatMapper :: toMessageResponse);

        log.info("Fetched {} messages for sessionId: {} ",response.getNumberOfElements(),sessionId);

        return response;
    }
}
