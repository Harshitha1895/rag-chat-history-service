package com.casestudy.rag_chat_history_service.security;

import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RateLimitFilter  extends OncePerRequestFilter {

    private final Bucket bucket;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(bucket.tryConsume(1))
        {
            filterChain.doFilter(request,response);

        }
        else {
            response.setStatus(429);
            response.setContentType("application/json");
            response.getWriter().write("""
                            {
                                "message": "Too many Requests"
                             }
                            """);
        }


    }
}
