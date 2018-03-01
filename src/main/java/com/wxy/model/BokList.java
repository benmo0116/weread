package com.wxy.model;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class BokList {
    private Integer id;

    @NotNull(message = "用户id不能为空")
    private Integer userid;

    private String username;
    @NotNull(message = "书本id不能为空")
    private Integer bookid;

    private String bookname;

    private String author;
    @NotNull(message = "年份不能为空")
    private Integer listyear;
    @Future
    private Date planstarttime;
    @Future
    private Date planfinishtime;

    private Date starttime;

    private Date finishtime;

    private Date listtime;

    private Boolean iffinished;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getListyear() {
        return listyear;
    }

    public void setListyear(Integer listyear) {
        this.listyear = listyear;
    }

    public Date getPlanstarttime() {
        return planstarttime;
    }

    public void setPlanstarttime(Date planstarttime) {
        this.planstarttime = planstarttime;
    }

    public Date getPlanfinishtime() {
        return planfinishtime;
    }

    public void setPlanfinishtime(Date planfinishtime) {
        this.planfinishtime = planfinishtime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public Date getListtime() {
        return listtime;
    }

    public void setListtime(Date listtime) {
        this.listtime = listtime;
    }

    public Boolean getIffinished() {
        return iffinished;
    }

    public void setIffinished(Boolean iffinished) {
        this.iffinished = iffinished;
    }
}