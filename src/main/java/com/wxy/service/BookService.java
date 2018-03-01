package com.wxy.service;

import com.wxy.model.Book;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-11 16:20
 * @desc ${DESCRIPTION}
 **/
public interface BookService extends IService<Book>{
    List<Book> selectBookByName(String bookName);
    Book selectBookById(int bookId);
}
