package com.zuijianren.blog.pojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Comment {
    private long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Timestamp createTime;
    private long blogId;
    private long parentCommentId;
    private long topCommentId;
    private Boolean admin;

    private Comment parentComment;  //必须包含 id, nickname, topCommentId
    private List<Comment> replyComments = new ArrayList<>();

  /*  private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();

    private Comment parentComment;*/

    public Comment() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public long getTopCommentId() {
        return topCommentId;
    }

    public void setTopCommentId(long topCommentId) {
        this.topCommentId = topCommentId;
    }

    public long getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(long parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
