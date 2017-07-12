package com.zbl.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zbl.shiro.ShiroUser;
import com.zbl.web.response.GetResponse;
import com.zbl.web.response.ResponseFactory;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zbl.util.Result;


public class BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public String sysName = "图书管理系统";

    // 获取基于应用程序的url绝对路径
    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        return request.getContextPath() + url;
    }

    public String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


    // 根据url获取当前控制器名称
    public String[] controllerName() {
        HttpServletRequest request = null;
        String url = request.getRequestURI();
        String[] urlArr = url.split("/");
        return urlArr;
    }

    /**
     * 返回字符串
     * @throws Exception
     */
    public String responsestr(HttpServletResponse response, String json) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(json);
        return null;
    }

    // 根据url获取当前操作名称
    public String actionName(String url) {
        return url;
    }


    /**
     * 获取页面传递的某一个参数值,
     */
    public String getPara(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameter(key);
    }

    /**
     * 获取页面传递的某一个数组值,
     * @return Class<T>
     * @throws Exception
     */
    public String[] getParaValues(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameterValues(key);
    }
    /*
     * @ModelAttribute
	 * 这个注解作用.每执行controllor前都会先执行这个方法
	 * @author 
	 * Email：
	 * date：2015-4-05
	 * @param request
	 * @throws Exception 
	 * @throws  
	 */
    /*@ModelAttribute
    public void init(HttpServletRequest request){
		String path = Common.BACKGROUND_PATH;
		Object ep = request.getSession().getAttribute("basePath");
		if(ep!=null){
			if(!path.endsWith(ep.toString())){
				Common.BACKGROUND_PATH = "/WEB-INF/jsp/background"+ep;
			}
		}
		
	}*/

    /**
     * 获取当前登录用户对象
     * @return {ShiroUser}
     */
    public ShiroUser getShiroUser() {
        Object obj= SecurityUtils.getSubject().getPrincipal();
        return (ShiroUser)obj;
    }

    /**
     * 获取当前登录用户id
     * @return {Long}
     */
    public Integer getUserId() {
        return this.getShiroUser().getId();
    }

    /**
     * 获取当前登录用户名
     * @return {String}
     */
    public String getStaffName() {
        return this.getShiroUser().getName();
    }



    /**
     * ajax失败
     *
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     *
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * ajax成功
     *
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     *
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }

    /**
     * 移动端Retrofit响应
     * 响应成功
     */
    public Object renderRetrofitSuccess(Object data) {
        return ResponseFactory.getInstance().getResponse(data);
    }

    public Object renderRetrofitSuccess() {
        return ResponseFactory.getInstance().getResponse();
    }

    /**
     * 移动端Retrofit响应
     * 响应失败
     */

    public Object renderRetrofitError() {
        return ResponseFactory.getInstance().getFailResponse();
    }


}
