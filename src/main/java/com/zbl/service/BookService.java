package com.zbl.service;

import com.zbl.entity.Book;
import com.zbl.util.PageInfo;

import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface BookService {
    void selectPage(PageInfo pageInfo);

    List<Book> selectAllBooks();

    Book selectByPrimaryKey(Long id);

    void insertBook(Book book);

    void updateByPrimaryKey(Book book);

    void deleteByPrimarKey(Long id);
}
