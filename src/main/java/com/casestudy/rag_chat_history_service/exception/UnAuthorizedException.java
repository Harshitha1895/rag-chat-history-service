package com.casestudy.rag_chat_history_service.exception;

public class UnAuthorizedException extends RuntimeException
{

    public UnAuthorizedException(String message)
    {
        super(message);
    }

}
