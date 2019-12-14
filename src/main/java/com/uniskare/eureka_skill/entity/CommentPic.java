package com.uniskare.eureka_skill.entity;

import java.util.Objects;

/**
 * @author : SCH001
 * @description :
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "comment_pic", schema = "sedb", catalog = "")
@javax.persistence.IdClass(CommentPicPK.class)
public class CommentPic {
    private int commentId;
    private int index;
    private String url;

    @javax.persistence.Id
    @javax.persistence.Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @javax.persistence.Id
    @javax.persistence.Column(name = "index")
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "url")
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
