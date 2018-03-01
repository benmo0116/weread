package com.wxy.mapper;

import com.wxy.model.Book;
import com.wxy.model.BookExample;
import com.wxy.model.User;

import java.util.List;

public interface BookMapper extends BaseMapper<Book>{
    int countByExample(BookExample example);

    int deleteByPrimaryKey(Integer bookid);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

}