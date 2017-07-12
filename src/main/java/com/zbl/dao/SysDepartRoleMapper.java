package com.zbl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zbl.entity.SysDepartRole;
import com.zbl.entity.SysResRole;

public interface SysDepartRoleMapper extends BaseMapper<SysDepartRole> {
    int deleteByPrimaryKey(Integer id);

    Integer insert(SysResRole record);

    int insertSelective(SysResRole record);

    SysResRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResRole record);

    int updateByPrimaryKey(SysResRole record);
    
    Integer selectIdListByRoleId(@Param("id") Integer id);
    List<Integer> selectDepartIdListByRoleId(Integer id) ;
    List<Integer> selectDepartByRoles(@Param("userId") Integer userId) ;
    
}