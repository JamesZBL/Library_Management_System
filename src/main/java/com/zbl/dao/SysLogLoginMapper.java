package com.zbl.dao;

import java.util.List;
import java.util.Map;

import com.zbl.entity.vo.SysLogLogin;

public interface SysLogLoginMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SysLogLogin record);

	int insertSelective(SysLogLogin record);

	SysLogLogin selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysLogLogin record);

	int updateByPrimaryKey(SysLogLogin record);

	List<SysLogLogin> selectPage(Map<String, Object> condition);
}