package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CommentPicPK implements Serializable {
    private int commentId;
    private int index;

    @Column(name = "comment_id")
    @Id
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Column(name = "index")
    @Id
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
