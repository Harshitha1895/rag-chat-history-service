package com.casestudy.rag_chat_history_service.mapper;

import com.casestudy.rag_chat_history_service.dto.response.ChatMessageResponse;
import com.casestudy.rag_chat_history_service.dto.response.ChatSessionResponse;
import com.casestudy.rag_chat_history_service.entity.ChatMessages;
import com.casestudy.rag_chat_history_service.entity.ChatSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatSessionResponse toSessionResponse(ChatSession chatSession);

    ChatMessageResponse toMessageResponse(ChatMessages chatMessages);
}
