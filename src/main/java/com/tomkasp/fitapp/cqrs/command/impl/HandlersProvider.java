package com.tomkasp.fitapp.cqrs.command.impl;


import com.tomkasp.fitapp.cqrs.command.handler.CommandHandler;

public interface HandlersProvider {
    CommandHandler<Object, Object> getHandler(Object command);
}
