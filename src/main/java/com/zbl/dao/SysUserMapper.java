package com.zbl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zbl.entity.SysUser;
import com.zbl.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface SysUserMapper extends BaseMapper<SysUser>{

	public List<SysUser> findUserPage(SysUser user);
	
	public List<SysUser> findByNames(SysUser user);
	
    int deleteByPrimaryKey(Integer id);

    Integer insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    UserVo selectUserVoById(@Param("id") Integer id);

    //List<UserVo> selectUserVoPage(Pagination page, Map<String, Object> params);
    
    List<UserVo> selectUserVoPage(Map<String, Object> params);
    
    int selectUserIdByUserName(String userName);
    
}
