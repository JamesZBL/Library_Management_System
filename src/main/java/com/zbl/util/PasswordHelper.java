package com.zbl.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.zbl.entity.SysUser;
import com.zbl.entity.vo.UserVo;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public void encryptPassword(SysUser user) {
		String salt=randomNumberGenerator.nextBytes().toHex();
		user.setCredentialssalt(salt);
		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getAccountname()+salt), hashIterations).toHex();
		user.setPassword(newPassword);
	}
	
	public void encryptPassword(UserVo userVo) {
		String salt=randomNumberGenerator.nextBytes().toHex();
		userVo.setCredentialssalt(salt);
		String newPassword = new SimpleHash(algorithmName, userVo.getPassword(), ByteSource.Util.bytes(userVo.getAccountname()+salt), hashIterations).toHex();
		userVo.setPassword(newPassword);
	}
	
	public Boolean IsPasswordMatched(String pwd,SysUser user) {
		String salt=user.getCredentialssalt();
		String strPassword = new SimpleHash(algorithmName, pwd, ByteSource.Util.bytes(user.getAccountname()+salt), hashIterations).toHex();
		String nowPassword = user.getPassword();
		return strPassword.equals(nowPassword);
	}
	
	
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		SysUser user = new SysUser();
		user.setAccountname("admin");
		user.setPassword("123456");
		passwordHelper.encryptPassword(user);
		System.out.println(user);
	}
}
