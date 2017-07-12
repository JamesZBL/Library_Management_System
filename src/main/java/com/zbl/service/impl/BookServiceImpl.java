package com.zbl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zbl.dao.AppointmentMapper;
import com.zbl.dao.BookMapper;
import com.zbl.entity.Book;
import com.zbl.service.BookService;
import com.zbl.util.PageInfo;

@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 注入Service依赖
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AppointmentMapper appointmentMapper;


    /**
     * 图书列表
     */
    @Override
    public void selectPage(PageInfo pageInfo) {
        try {
            PageHelper.startPage(pageInfo.getNowpage(), pageInfo.getSize());
            List<Book> list = bookMapper.selectPage(pageInfo.getCondition());
            com.github.pagehelper.PageInfo<Book> pageinf = new com.github.pagehelper.PageInfo<Book>(list);
            pageInfo.setRows(pageinf.getList());
            pageInfo.setTotal(new Long(pageinf.getTotal()).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Book selectByPrimaryKey(Long id) {
        return bookMapper.selectByPrimaryKey(id);
    }


    @Override
    public void insertBook(Book book) {
        try {
            bookMapper.insert(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateByPrimaryKey(Book book) {
        try {
            bookMapper.updateByPrimaryKey(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteByPrimarKey(Long id) {
        try {
            bookMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /************************** Mobile 开始 ************************/
    /**
     * 查询所有图书
     *
     * @return
     */
    @Override
    public List<Book> selectAllBooks() {
        return bookMapper.selectAll();
    }
}
