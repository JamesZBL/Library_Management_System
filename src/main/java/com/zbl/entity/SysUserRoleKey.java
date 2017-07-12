package com.zbl.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zbl.util.JsonUtils;


/**
*
* 用户角色
*
*/
@TableName("sys_user_role")
public class SysUserRoleKey {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	
	/** 主键id */
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/** 用户id */
	@TableField(value = "user_id")
    private Integer userid;

	/** 角色id */
	@TableField(value = "role_id")
    private Integer roleid;

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
    
    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}