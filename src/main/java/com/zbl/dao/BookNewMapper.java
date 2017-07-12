package com.zbl.dao;

import com.zbl.entity.BookNew;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookNewMapper {

    int deleteByPrimaryKey(Long bookId);

    int insert(BookNew record);

    int insertSelective(BookNew record);

    BookNew selectByPrimaryKey(Long bookId);

    int updateByPrimaryKey(BookNew record);
}