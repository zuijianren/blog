package com.zuijianren.blog.model;

//博客查询条件
public class BlogRequest {
    private String title;
    private long typeId;
    private Boolean recommend;

    public BlogRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        if(typeId == null || typeId == "") {
            this.typeId = 0;
        }else{
            this.typeId = new Long(typeId);
        }

    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }
}
