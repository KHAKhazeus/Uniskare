package com.uniskare.eureka_user.entity;
// CHECKSTYLE:OFF
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment_pic", schema = "sedb", catalog = "")
@IdClass(CommentPicPK.class)
public class CommentPic {
    private int commentId;
    private int index;
    private String url;

    @Id
    @Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Id
    @Column(name = "index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentPic that = (CommentPic) o;
        return commentId == that.commentId &&
                index == that.index &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, index, url);
    }
}
