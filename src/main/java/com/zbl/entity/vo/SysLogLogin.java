package com.zbl.entity.vo;

public class SysLogLogin {
	private Integer id;

	private String username;

	private String clientip;

	private String logintime;

	private Integer type;

	
	public SysLogLogin()
	{
		
		
	}
	
	public SysLogLogin(String username, String clientip) {
		super();
		this.username = username;
		this.clientip = clientip;
	}

	public SysLogLogin(String username, String clientip,  Integer type) {
		super();
		this.username = username;
		this.clientip = clientip;
		this.type = type;
	}

	public SysLogLogin(String username, String clientip, String logintime, Integer type) {
		super();
		this.username = username;
		this.clientip = clientip;
		this.logintime = logintime;
		this.type = type;
	}

	public SysLogLogin(Integer id, String username, String clientip, String logintime, Integer type) {
		super();
		this.id = id;
		this.username = username;
		this.clientip = clientip;
		this.logintime = logintime;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getClientip() {
		return clientip;
	}

	public void setClientip(String clientip) {
		this.clientip = clientip == null ? null : clientip.trim();
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}