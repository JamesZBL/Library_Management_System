package com.zbl.dao;

import java.util.List;
import java.util.Map;

import com.zbl.entity.Book;

public interface BookMapper {
    int deleteByPrimaryKey(Long bookId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long bookId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectPage(Map<String, Object> condition);

    List<Book> selectAll();
}