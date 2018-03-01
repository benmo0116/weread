package com.wxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxy.mapper.BookMapper;
import com.wxy.model.Book;
import com.wxy.model.BookExample;
import com.wxy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-11 16:21
 * @desc ${DESCRIPTION}
 **/
@Service(value = "bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return bookMapper.selectAll();
    }

    @Override
    public List<Book> selectBookByName(String bookName) {
        BookExample bookExample = new BookExample();
        bookExample.or().andBookname2EqualTo(bookName);
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public Book selectBookById(int bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public int insertOne(Book book) {
        return bookMapper.insertSelective(book);
    }

    @Override
    public int deleteOneByPrimaryKey(Integer userid) {
        return 0;
    }

    @Override
    public int updateOne(Book book) {
        return 0;
    }

    @Override
    public Book selectOneByPrimaryKey(Integer userid) {
        return null;
    }

    @Override
    public boolean exist(int id) {
        return bookMapper.exist(id) == 1;
    }
}
