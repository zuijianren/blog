package com.zuijianren.blog.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Blog {
    private long id;
    private String title;//标题
    private String content;//内容
    private String firstPicture;//首图
    private String flag;//原创还是转载
    private long views;//浏览人数
    private Boolean appreciation = false;//是否开启赞赏
    private Boolean shareStatement = false;//是否开启版权显示
    private Boolean commentabled = false;//是否允许评论
    private Boolean published = false;//是否发布
    private Boolean recommend = false;//是否推荐
    private Timestamp createTime;//发布时间
    private Timestamp updateTime;//最后更新时间
    private String description;//blog描述

    private Type type;

    private User user;


  /*  private Type type;
    private List<Tag> tags = new ArrayList<>();

    private User user;
    private List<Comment> comments = new ArrayList<>();
*/
    public Blog() {
    }

    //自定义方法
    public void setId(){
        Date date = new Date();
        long time = date.getTime();
        setId(time);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public Boolean getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(Boolean appreciation) {
        this.appreciation = appreciation;
    }

    public Boolean getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(Boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public Boolean getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(Boolean commentabled) {
        this.commentabled = commentabled;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
