package com.wangjun.pojo;

import java.util.Date;

public class NoticeInf {
    private Integer id;

    private String title;

    private Date createDate;

    private Integer userId;

    private String content;
    
    private UserInf user;
    

    public UserInf getUser() {
		return user;
	}

	public void setUser(UserInf user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public String toString() {
		return "NoticeInf [id=" + id + ", title=" + title + ", createDate=" + createDate + ", userId=" + userId
				+ ", content=" + content + ", user=" + user + "]";
	}
}