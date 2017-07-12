package com.zbl.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zbl.entity.SysRole;
import com.zbl.entity.SysUser;
import com.zbl.util.JsonUtils;

/**
 * @description：UserVo
 * @author：
 * @date：
 */
public class UserVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String accountname;

	private String username;

	@JsonIgnore
	private String password;

	private Integer sex;

	private Integer age;

	private Integer userType;

	private Integer locked;

	private Integer organizationId;

	private Date createtime;

	private String phone;

	private List<SysRole> rolesList;

	private String organizationName;

	private String roleIds;
	
	private String roleNames;

	private Date createdateStart;
	private Date createdateEnd;
	
	private String credentialssalt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {		
		this.accountname = accountname == null ? null : accountname.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {		
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	

	public String getCredentialssalt() {
		return credentialssalt;
	}

	public void setCredentialssalt(String credentialssalt) {
		this.credentialssalt = credentialssalt;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}



	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public List<SysRole> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<SysRole> rolesList) {
		this.rolesList = rolesList;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Date getCreatedateStart() {
		return createdateStart;
	}

	public void setCreatedateStart(Date createdateStart) {
		this.createdateStart = createdateStart;
	}

	public Date getCreatedateEnd() {
		return createdateEnd;
	}

	public void setCreatedateEnd(Date createdateEnd) {
		this.createdateEnd = createdateEnd;
	}
	
	
	
	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * 比较vo和数据库中的用户是否同一个user，采用id比较
	 * @param user 用户
	 * @return 是否同一个人
	 */
	public boolean equalsUser(SysUser user) {
		if (user == null) {
			return false;
		}
		Integer userId = user.getId();
		if (id == null || userId == null) {
			return false;
		}
		return id.equals(userId);
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}