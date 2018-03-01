package com.wxy.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Record {
    private Integer recordid;

    private Date recordtime;
    @NotNull(message="进度不能为空")
    @DecimalMax("1")
    private Double readpercent;
    @NotNull(message="用户id不能为空")
    private Integer userid;

    private String nickname;
    @NotNull(message="书本id不能为空")
    private Integer bookid;

    private String bookname;

    private String bookname2;

    private String author;

    private String country;
    private String readpages;

    public String getReadpages() {
        return readpages;
    }

    public void setReadpages(String readpages) {
        this.readpages = readpages;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public Double getReadpercent() {
        return readpercent;
    }

    public void setReadpercent(Double readpercent) {
        this.readpercent = readpercent;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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

    public String getBookname2() {
        return bookname2;
    }

    public void setBookname2(String bookname2) {
        this.bookname2 = bookname2 == null ? null : bookname2.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordid=" + recordid +
                ", recordtime=" + recordtime +
                ", readpercent=" + readpercent +
                ", userid=" + userid +
                ", nickname='" + nickname + '\'' +
                ", bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", bookname2='" + bookname2 + '\'' +
                ", author='" + author + '\'' +
                ", country='" + country + '\'' +
                ", readpages='" + readpages + '\'' +
                '}';
    }
}