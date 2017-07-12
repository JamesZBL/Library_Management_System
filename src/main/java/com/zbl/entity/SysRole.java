package com.zbl.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.zbl.util.JsonUtils;

@TableName("sys_role")
public class SysRole {
	
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键id */
	@TableId(type = IdType.AUTO)
    private Integer id;

    private String state;

    private String name;

    private String rolekey;

    private String description;
    
    
    /** 排序号 */
	private Integer seq;

	/** 状态 */
	private Integer status;
	
	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRolekey() {
        return rolekey;
    }

    public void setRolekey(String rolekey) {
        this.rolekey = rolekey == null ? null : rolekey.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}