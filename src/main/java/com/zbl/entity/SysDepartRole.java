package com.zbl.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zbl.util.JsonUtils;


/**
*
* 角色资源
*
*/
@TableName("sys_depart_role")
public class SysDepartRole {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
    private Integer id;

	/** 部门id */
	@TableField(value = "departId")
    private String departId;

    /** 角色id */
	@TableField(value = "roleId")
    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}
}