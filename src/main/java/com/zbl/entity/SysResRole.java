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
@TableName("sys_res_role")
public class SysResRole {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
    private Integer id;

	/** 资源id */
	@TableField(value = "resId")
    private String resid;

    /** 角色id */
	@TableField(value = "roleId")
    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid == null ? null : resid.trim();
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