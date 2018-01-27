package com.chat.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chat.app.model.ChatMessage;

/**
 * The listener interface for receiving webSocketEvent events.
 * The class that is interested in processing a webSocketEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addWebSocketEventListener<code> method. When
 * the webSocketEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @author mudiganti.phanees
 */
@Component
public class WebSocketEventListener {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    /** The messaging template. */
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    /**
     * Handle web socket connect listener.
     *
     * @param event the event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    /**
     * Handle web socket disconnect listener.
     *
     * @param event the event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            logger.info("User Disconnected : " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
