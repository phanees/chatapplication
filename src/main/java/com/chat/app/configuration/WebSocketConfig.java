package com.chat.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * The Class WebSocketConfig.
 *
 * @author mudiganti.phanees
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * Register stomp endpoints.
     *
     * @param registry the registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /* (non-Javadoc)
     * @see org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer#configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry)
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");  
    }
}
