package com.zbl.service.impl;

import java.util.Date;
import java.util.List;

import com.zbl.dao.SysUserMapper;
import com.zbl.dao.SysUserRoleMapper;
import com.zbl.entity.SysUser;
import com.zbl.entity.SysUserRoleKey;
import com.zbl.entity.vo.UserVo;
import com.zbl.service.IUserService;
import com.zbl.util.BeanUtils;
import com.zbl.util.PageInfo;
import com.zbl.util.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;


/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IUserService {

	private static final String TAG = "IUserService";

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;

	protected Logger logger = Logger.getLogger(UserServiceImpl.class);

	public List<SysUser> selectByLoginName(UserVo userVo) {
		logger.error(TAG+"："+"selectByLoginName");
		try {
			SysUser user = new SysUser();
			user.setAccountname(userVo.getAccountname());
			EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>(user);
			if (null != userVo.getId()) {
				wrapper.where("id != {0}", userVo.getId());
			}
			return this.selectList(wrapper);
		} catch (Exception e) {
			logger.error(TAG+"："+"selectByLoginName异常"+StringEscapeUtils.escapeSql(e.getMessage()));
		}

		return null;
	}

	public void insertByVo(UserVo userVo) {
		logger.error(TAG+"："+"insertByVo");
		try {
			SysUser user = BeanUtils.copy(userVo, SysUser.class);
			user.setCreateTime(new Date());
			this.insert(user);
			// Integer id = user.getId();
			Integer id = this.selectByLoginName(userVo).get(0).getId();
			String[] roles = userVo.getRoleIds().split(",");
			SysUserRoleKey userRole = new SysUserRoleKey();

			for (String string : roles) {
				userRole.setUserid(id);
				userRole.setRoleid(Integer.valueOf(string));
				userRoleMapper.insert(userRole);
			}
		} catch (NumberFormatException e) {
			logger.error(TAG+"："+"insertByVo异常"+StringEscapeUtils.escapeSql(e.getMessage()));
		}
	}

	public UserVo selectVoById(Integer id) {
		logger.error(TAG+"："+"selectVoById");
		try {
			return userMapper.selectUserVoById(id);
		} catch (Exception e) {
			logger.error(TAG+"："+"selectVoById异常"+StringEscapeUtils.escapeSql(e.getMessage()));
		}
		return null;
	}

	public void updateByVo(UserVo userVo) {
		logger.error(TAG+"："+"updateByVo");
		try {
			SysUser user = BeanUtils.copy(userVo, SysUser.class);
			if (StringUtils.isBlank(user.getPassword())) {
				user.setPassword(null);
			}
			this.updateById(user);

			Integer id = userVo.getId();
			List<SysUserRoleKey> userRoles = userRoleMapper.selectByUserId(id);
			if (userRoles != null && !userRoles.isEmpty()) {
				for (SysUserRoleKey userRole : userRoles) {
					userRoleMapper.deleteById(userRole.getId());
				}
			}

			String[] roles = userVo.getRoleIds().split(",");
			SysUserRoleKey userRole = new SysUserRoleKey();
			for (String string : roles) {
				userRole.setUserid(id);
				userRole.setRoleid(Integer.valueOf(string));
				userRoleMapper.insert(userRole);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(TAG+"："+StringEscapeUtils.escapeSql(e.getMessage()));
		}

	}

	public void updatePwdByUserId(Integer userId, String md5Hex) {
		logger.error(TAG+"："+"updatePwdByUserId");
		try {
			SysUser user = new SysUser();
			user.setId(userId);
			user.setPassword(md5Hex);
			this.updateById(user);
		} catch (Exception e) {
			logger.error(TAG+"："+"updatePwdByUserId异常"+StringEscapeUtils.escapeSql(e.getMessage()));
		}
	}

	public void selectDataGrid(PageInfo pageInfo) {
		/*
		 * Page<UserVo> page = new Page<UserVo>(pageInfo.getNowpage(),
		 * pageInfo.getSize()); List<UserVo> list =
		 * userMapper.selectUserVoPage(page, pageInfo.getCondition());
		 * pageInfo.setRows(list); pageInfo.setTotal(page.getTotal());
		 */
		logger.error(TAG+"："+"selectDataGrid");
		try {
			PageHelper.startPage(pageInfo.getNowpage(), pageInfo.getSize());
			List<UserVo> list = userMapper.selectUserVoPage(pageInfo.getCondition());
			com.github.pagehelper.PageInfo<UserVo> pageinf = new com.github.pagehelper.PageInfo<UserVo>(list);
			pageInfo.setRows(list);
			pageInfo.setTotal(new Long(pageinf.getTotal()).intValue());
		} catch (Exception e) {
			logger.error(TAG+"："+"selectDataGrid异常"+StringEscapeUtils.escapeSql(e.getMessage()));
		}
	}

	public void deleteUserById(Integer id) {
		logger.error(TAG+"："+"deleteUserById");
		try {
			this.deleteById(id);
			List<SysUserRoleKey> userRoles = userRoleMapper.selectByUserId(id);
			if (userRoles != null && !userRoles.isEmpty()) {
				for (SysUserRoleKey userRole : userRoles) {
					userRoleMapper.deleteById(userRole.getId());
				}
			}
		} catch (Exception e) {
			logger.error(TAG+"："+"deleteUserById异常"+StringEscapeUtils.escapeSql(e.getMessage()));
		}
	}

}