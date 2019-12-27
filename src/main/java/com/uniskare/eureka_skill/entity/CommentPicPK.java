package com.uniskare.eureka_skill.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CommentPicPK implements Serializable {
    private int commentId;
    private int pindex;

    @Column(name = "comment_id")
    @Id
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Column(name = "pindex")
    @Id
    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentPicPK that = (CommentPicPK) o;
        return commentId == that.commentId &&
                pindex == that.pindex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, pindex);
    }
}
