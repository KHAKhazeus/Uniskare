package com.uniskare.eureka_skill.entity;

import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
public class Conversation {
    private int conversationId;
    private String userId;
    private String otherId;
    private Byte onTop;
    private Integer unread;

    @javax.persistence.Id
    @javax.persistence.Column(name = "conversation_id")
    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "other_id")
    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "on_top")
    public Byte getOnTop() {
        return onTop;
    }

    public void setOnTop(Byte onTop) {
        this.onTop = onTop;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "unread")
    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return conversationId == that.conversationId &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(otherId, that.otherId) &&
                Objects.equals(onTop, that.onTop) &&
                Objects.equals(unread, that.unread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, userId, otherId, onTop, unread);
    }
}
