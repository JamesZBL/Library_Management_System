package com.zbl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 字符加密处理工具类
 * 
 * @author 
 * @date 
 */
public class MD5Encoder {

	private MessageDigest md = null;

	public MD5Encoder() {
		initialize();
	}

	/**
	 * 初始化操作
	 * */
	private void initialize() {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密
	 * */
	public String encode(String str) {
		if (md == null)
			return null;
		if (str == null || "".equals(str))
			return str;

		String result = null;
		md.update(str.getBytes());
		byte[] byteResult = md.digest();

		BASE64Encoder b64encoder = new BASE64Encoder();
		result = b64encoder.encode(byteResult);

		md.reset();
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String password="wangliang";
		MD5Encoder md5 = new MD5Encoder();
		String passwordMd5 = md5.encode(password);
		System.out.println(passwordMd5);
	}

}
