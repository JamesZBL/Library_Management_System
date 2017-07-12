package com.zbl.util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSON;

/**
 * AJAX 请求时返回字符工具类；
 * 
 * @author 
 * @date 
 */
public class AjaxResponse {
	/**
	 * 返回JSON格式的字符串对象，如果对象为空，则返回“”；
	 * 
	 * @param json
	 * @param response
	 */
	public static void sendAjaxJSON(JSON json, HttpServletResponse response) {
		response.setContentType(ContentType.JSON);
		response.setCharacterEncoding(Charset.UTF_8);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (json == null) {
				out.write("");
			} else {
				out.write(json.toString());
			}
			out.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception ex) {
				}
			}
		}
	}

	/**
	 * 返回指定格式的字符串对象，如果对象为空，则返回“”；
	 * 
	 * @param content
	 *            需要返回的字符串
	 * @param response
	 *            响应对象，用来获取返回的输出流
	 * @param contentType
	 *            返回类型：
	 * @param charset
	 *            返回字符编码：默认为：UTF－8
	 */
	public static void sendAjaxText(String content,HttpServletResponse response, String contentType, String charset) {
		response.setContentType(contentType == null ? ContentType.TEXT: contentType);
		response.setCharacterEncoding(charset == null ? Charset.UTF_8 : charset);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (content == null) {
				out.write("");
			} else {
				out.write(content);
			}
			out.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception ex) {
				}
			}
		}
	}
}
