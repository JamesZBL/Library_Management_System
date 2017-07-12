/**
 * @since 2008-9-5,上午09:38:30
 */
package com.zbl.util;

import java.io.UnsupportedEncodingException;

/**
 * Get请求参数编码类
 * 
 * @author 
 */
public class GetRequestParameterEncoding {
	private boolean needEncoding; // 是否需要转码

	private String sourceEncoding = "iso8859_1";	//字符源编码

	private String paramEncoding = "utf-8";		//字符目标编码

	/**
	 * @param needEncoding
	 *            要设置的 needEncoding
	 */
	public void setNeedEncoding(boolean needEncoding) {
		this.needEncoding = needEncoding;
	}

	/**
	 * @param sourceEncoding
	 *            the sourceEncoding to set
	 */
	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	/**
	 * @param paramEncoding
	 *            要设置的 paramEncoding
	 */
	public void setParamEncoding(String paramEncoding) {
		this.paramEncoding = paramEncoding;
	}

	public String getEncodedString(String s) {
		if (this.needEncoding) {
			try {
				return new String(s.getBytes(this.sourceEncoding), this.paramEncoding);
			} catch (UnsupportedEncodingException e) {
				return s;
			}
		}
		return s;
	}

}
