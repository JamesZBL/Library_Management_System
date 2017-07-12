package com.zbl.web;

import java.io.IOException;
import java.util.Date;

import com.zbl.dao.SysLogLoginMapper;
import com.zbl.entity.vo.SysLogLogin;
import com.zbl.util.Common;
import com.zbl.util.DateFormatter;
import com.zbl.web.base.BaseController;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/") 
public class BGController extends BaseController{

	private String viewPath="";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private SysLogLoginMapper loginMapper;

	@Autowired
	public void setLoginMapper(SysLogLoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}
	
	//主页
	@RequestMapping("/")
	public String index(){
		return viewPath+"index";
	}


	@RequestMapping(value = "login", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.removeAttribute("error");
		return "/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Object logout() {
		logger.info("登出");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return renderSuccess();
	}

	@RequestMapping(value = "index", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.removeAttribute("error");
		return "index";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String login(String username, String password, HttpServletRequest request, RedirectAttributes attr) {


		String logintype = request.getParameter("logintype");
		String error = "";
		SysLogLogin loginVo = null;

		try {
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error", "支持POST方法提交！");
			}
			if (Common.isEmpty(username) || Common.isEmpty(password)) {
				error = "用户名或密码不能为空！";
				request.setAttribute("error", error);
				if (StringUtils.isNotEmpty(logintype) && logintype.equals("mobile")) {

					attr.addAttribute("error", error);
					return "redirect:phone/login";
				}
				return "/login";

			}
			// 想要得到 SecurityUtils.getSubject() 的对象．．访问地址必须跟shiro的拦截地址内．不然后会报空指针
			Subject user = SecurityUtils.getSubject();
			// 用户输入的账号和密码,,存到UsernamePasswordToken对象中..然后由shiro内部认证对比,
			// 认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
			// 当以上认证成功后会向下执行,认证失败会抛出异常
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				user.login(token);
			} catch (LockedAccountException lae) {
				token.clear();
				error = "用户已经被锁定不能登录，请与管理员联系！";
				request.setAttribute("error", error);
				if (StringUtils.isNotEmpty(logintype) && logintype.equals("mobile")) {
					attr.addAttribute("error", error);
					return "redirect:phone/login";
				}
				return "/login";
			} catch (ExcessiveAttemptsException e) {
				token.clear();
				error = "账号：" + username + " 登录失败次数过多,锁定10分钟!";
				request.setAttribute("error", error);
				if (StringUtils.isNotEmpty(logintype) && logintype.equals("mobile")) {
					attr.addAttribute("error", error);
					return "redirect:phone/login";
				}
				return "/login";
			} catch (AuthenticationException e) {
				System.out.println(e.getMessage());
				token.clear();
				error = "用户或密码不正确！";
				request.setAttribute("error", error);
				if (StringUtils.isNotEmpty(logintype) && logintype.equals("mobile")) {
					attr.addAttribute("error", error);
					return "redirect:phone/login";
				}
				return "/login";

			} catch (Exception e) {
				System.out.println(e.getMessage());
				error = e.getMessage();
				request.setAttribute("error", error);
				if (StringUtils.isNotEmpty(logintype) && logintype.equals("mobile")) {
					attr.addAttribute("error", error);
					return "redirect:phone/login";
				}
				return "/login";
			}
			Session session;
			session = SecurityUtils.getSubject().getSession();
			/*
			 * userLogin.put("userId", session.getAttribute("userSessionId"));
			 * userLogin.put("accountName", username); userLogin.put("loginIP",
			 * session.getHost()); userLoginMapper.addEntity(userLogin);
			 */
			request.removeAttribute("error");
		} catch (Exception e) {
			e.printStackTrace();
			error = "登录异常，请联系管理员！";
			request.setAttribute("error", error);
			if (StringUtils.isNotEmpty(logintype) && logintype.equals("mobile")) {
				attr.addAttribute("error", error);
				return "redirect:phone/login";
			}
			return "/login";
		}

		if (StringUtils.isNotEmpty(logintype) && logintype.equals("mobile")) {
			DateFormatter df = new DateFormatter();
			String strdate = df.formatDateTime(new Date());
			Session session;
			session = SecurityUtils.getSubject().getSession();
			session.setAttribute("logintype", "mobile");
			loginVo = new SysLogLogin(username, getRemoteHost(request), strdate, 1);
			loginMapper.insert(loginVo);
			return "redirect:phone/index";
		}

		// 记录登录信息到数据库
		DateFormatter df = new DateFormatter();
		String strdate = df.formatDateTime(new Date());
		loginVo = new SysLogLogin(username, getRemoteHost(request), strdate, 0);
		loginMapper.insert(loginVo);
		return "redirect:index";
	}
}
