package com.zbl.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zbl.entity.SysResRole;

public interface SysResRoleMapper extends BaseMapper<SysResRole> {
    int deleteByPrimaryKey(Integer id);

    Integer insert(SysResRole record);

    int insertSelective(SysResRole record);

    SysResRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResRole record);

    int updateByPrimaryKey(SysResRole record);
    
    Integer selectIdListByRoleId(@Param("id") Integer id);
    
}