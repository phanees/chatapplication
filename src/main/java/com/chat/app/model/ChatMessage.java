package com.chat.app.model;

/**
 * The Class ChatMessage.
 *
 * @author mudiganti.phanees
 */
public class ChatMessage {
    
    /** The type. */
    private MessageType type;
    
    /** The content. */
    private String content;
    
    /** The sender. */
    private String sender;

    /**
     * The Enum MessageType.
     */
    public enum MessageType {
        
        /** The chat. */
        CHAT,
        
        /** The join. */
        JOIN,
        
        /** The leave. */
        LEAVE
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(MessageType type) {
        this.type = type;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content.
     *
     * @param content the new content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the sender.
     *
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Sets the sender.
     *
     * @param sender the new sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
}
