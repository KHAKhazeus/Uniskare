package com.uniskare.eureka_skill.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment_pic", schema = "sedb", catalog = "")
@IdClass(CommentPicPK.class)
public class CommentPic {
    private int commentId;
    private int pindex;
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
    @Column(name = "pindex")
    public int getPindex() {
        return pindex;
    }

    public void setPindex(int pindex) {
        this.pindex = pindex;
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
                pindex == that.pindex &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, pindex, url);
    }
}
