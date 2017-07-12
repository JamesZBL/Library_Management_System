package com.zbl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zbl.entity.SysUserRoleKey;

public interface SysUserRoleMapper   extends BaseMapper<SysUserRoleKey> {
    int deleteByPrimaryKey(SysUserRoleKey key);

    Integer insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);
    
    List<SysUserRoleKey> selectByUserId(@Param("userId") Integer userId);

    List<Integer> selectRoleIdListByUserId(@Param("userId") Integer userId);
    
    
    List<String> selectRoleNameListByUserId(@Param("userId") Integer userId);    
    
    List<String> selectUrlListByUserId(@Param("userId") Integer userId);
}