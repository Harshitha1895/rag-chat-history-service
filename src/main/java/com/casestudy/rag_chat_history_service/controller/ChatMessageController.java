package com.casestudy.rag_chat_history_service.controller;

import com.casestudy.rag_chat_history_service.dto.request.AddMessageRequest;
import com.casestudy.rag_chat_history_service.dto.response.ChatMessageResponse;
import com.casestudy.rag_chat_history_service.service.ChatMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/sessions/{sessionId}/messages")
@RequiredArgsConstructor
public class ChatMessageController
{

    private final ChatMessageService chatMessageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChatMessageResponse addMessage(
            @PathVariable UUID sessionId,
            @Valid @RequestBody AddMessageRequest request
            )
    {
        return chatMessageService.addMessage(sessionId, request);
    }

    @GetMapping
    public Page<ChatMessageResponse> getMessages(
            @PathVariable UUID sessionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    )
    {
        return chatMessageService.getMessage(sessionId, page, size);
    }


}
