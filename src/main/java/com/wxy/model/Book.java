package com.wxy.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    private Integer bookid;

    @NotEmpty(message="作者不能为空")
    private String author;

    @NotNull(message="字数不要为空，可填0")
    private Long wordcount;

    @NotEmpty(message="国籍不能为空")
    private String country;

    @NotEmpty(message="书名不能为空")
    private String bookname;
    private String bookname2;

    @Size(max = 4, message="类型名不能超过四个字")
    private String booktype;



    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Long getWordcount() {
        return wordcount;
    }

    public void setWordcount(Long wordcount) {
        this.wordcount = wordcount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype == null ? null : booktype.trim();
    }

    public String getBookname2() {
        return bookname2;
    }

    public void setBookname2(String bookname2) {
        this.bookname2 = bookname2 == null ? null : bookname2.trim();
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", author='" + author + '\'' +
                ", wordcount=" + wordcount +
                ", country='" + country + '\'' +
                ", bookname='" + bookname + '\'' +
                ", booktype='" + booktype + '\'' +
                ", bookname2='" + bookname2 + '\'' +
                '}';
    }
}