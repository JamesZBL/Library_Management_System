package com.zbl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zbl.entity.SysResource;
import com.zbl.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper  extends BaseMapper<SysRole>{
    int deleteByPrimaryKey(Integer id);

    Integer insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<Integer> selectResourceIdListByRoleId(@Param("id") Integer id);
    List<Integer> selectDepartIdListByRoleId(@Param("id") Integer id);
    

    List<SysResource> selectResourceListByRoleIdList(@Param("list") List<Integer> list);

    List<Map<Integer, String>> selectResourceListByRoleId(@Param("id") Integer id);

    //List<SysRole> selectRoleList(Pagination page, @Param("sort") String sort, @Param("order") String order);
    
    List<SysRole> selectRoleList(@Param("sort") String sort, @Param("order") String order);
    
}