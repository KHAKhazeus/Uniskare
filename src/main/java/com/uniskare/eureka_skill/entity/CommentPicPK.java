package com.uniskare.eureka_skill.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
public class CommentPicPK implements Serializable {
    private int commentId;
    private int index;

    @javax.persistence.Column(name = "comment_id")
    @javax.persistence.Id
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @javax.persistence.Column(name = "index")
    @javax.persistence.Id
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentPicPK that = (CommentPicPK) o;
        return commentId == that.commentId &&
                index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, index);
    }
}
