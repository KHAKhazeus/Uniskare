package com.uniskare.eureka_user.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Conversation {
    private int conversationId;
    private String userId;
    private String otherId;
    private Byte onTop;
    private Integer unread;

    @Id
    @Column(name = "conversation_id")
    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "other_id")
    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    @Basic
    @Column(name = "on_top")
    public Byte getOnTop() {
        return onTop;
    }

    public void setOnTop(Byte onTop) {
        this.onTop = onTop;
    }

    @Basic
    @Column(name = "unread")
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
