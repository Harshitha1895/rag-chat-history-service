package com.casestudy.rag_chat_history_service.controller;

import com.casestudy.rag_chat_history_service.dto.request.CreateSessionRequest;
import com.casestudy.rag_chat_history_service.dto.request.RenameSessionRequest;
import com.casestudy.rag_chat_history_service.dto.response.ChatSessionResponse;
import com.casestudy.rag_chat_history_service.service.ChatSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ChatSessionResponse createChatSession(@Valid @RequestBody CreateSessionRequest request)
    {
        return chatSessionService.createChatSession(request);
    }

    @GetMapping
    public List<ChatSessionResponse> getChatSessions(@RequestParam String userId)
    {
         return chatSessionService.getChatSessions(userId);
    }

    @PatchMapping("/{sessionId}/rename")
    public ChatSessionResponse renameSession(
            @PathVariable UUID sessionId,
            @RequestBody RenameSessionRequest request
            )
    {
        return chatSessionService.renameSession(sessionId, request.title());
    }

    @PatchMapping("/{sessionId}/favorite")
    public ChatSessionResponse markAsFavorite(@PathVariable UUID sessionId)
    {
        return chatSessionService.markAsFavorite(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public void deleteSession(@PathVariable UUID sessionId)
    {
        chatSessionService.deleteSession(sessionId);
    }

}
