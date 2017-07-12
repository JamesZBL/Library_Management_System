package com.zbl.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zbl.util.JsonUtils;

/**
 *
 * 用户
 *
 */
@TableName("sys_user")
public class SysUser implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 登陆名 */
	@TableField(value = "accountname")
	private String accountname;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;
	
	private String credentialssalt;
	
	private String description;
	
	private Date createtime;
	
	private Integer deletestatus;

	/** 性别 */
	private Integer sex;

	/** 年龄 */
	private Integer age;

	/** 手机号 */
	private String phone;

	/** 用户类别 */
	@TableField(value = "user_type")
	private Integer userType;

	/** 用户状态 */
	private Integer locked;

	/** 所属机构 */
	@TableField(value = "organization_id")
	private Integer organizationId;

	/** 创建时间 */
	@TableField(value = "createtime")
	private Date createTime;


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCredentialssalt() {
		return credentialssalt;
	}

	public void setCredentialssalt(String credentialssalt) {
		this.credentialssalt = credentialssalt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(Integer deletestatus) {
		this.deletestatus = deletestatus;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
