package com.zbl.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zbl.util.PageInfo;
import com.zbl.entity.SysUser;
import com.zbl.entity.vo.UserVo;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IService<SysUser> {

    List<SysUser> selectByLoginName(UserVo userVo);

    void insertByVo(UserVo userVo);

    UserVo selectVoById(Integer id);

    void updateByVo(UserVo userVo);

    void updatePwdByUserId(Integer userId, String md5Hex);

    void selectDataGrid(PageInfo pageInfo);

    void deleteUserById(Integer id);
}