package com.zbl.shiro;

import com.zbl.dao.SysDepartRoleMapper;
import com.zbl.dao.SysUserMapper;
import com.zbl.dao.SysUserRoleMapper;
import com.zbl.entity.SysUser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 自定义Realm,进行数据源配置
 * 
 * @author  2014-12-25
 * @Email: 
 * @version 
 */
public class MyRealm extends AuthorizingRealm {

	@Inject
	private SysUserMapper userMapper;	
	
	@Inject
	private SysUserRoleMapper userRoleMapper;	
	
	@Inject
	private SysDepartRoleMapper sysDepartRoleMapper;
	
	protected Logger logger = Logger.getLogger(MyRealm.class);
	 
	/**
	 * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		if (loginName != null) {
			String userId = SecurityUtils.getSubject().getSession().getAttribute("userSessionId").toString();
	        
			//List<ResFormMap> rs = resourcesMapper.findUserResourcess(userId);
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色集合
			// info.addRole("default");
			// 用户的角色集合
			// info.setRoles(user.getRolesName());
			// 用户的角色对应的所有权限，如果只使用角色定义访问权限
			/*for (ResFormMap resources : rs) {
				info.addStringPermission(resources.get("resKey").toString());
			}*/			
			ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();	
	        info.setRoles(shiroUser.getRoles());
	        info.addStringPermissions(shiroUser.getUrlSet());		        
		        
			return info;
		}
		return null;
	}

	/**
	 * 认证回调函数,登录时调用
	 * 首先根据传入的用户名获取User信息；然后如果user为空，那么抛出没找到帐号异常UnknownAccountException；
	 * 如果user找到但锁定了抛出锁定异常LockedAccountException；最后生成AuthenticationInfo信息，
	 * 交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配，
	 * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
	 * 另外如果密码重试此处太多将抛出超出重试次数异常ExcessiveAttemptsException；
	 * 在组装SimpleAuthenticationInfo信息时， 需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt），
	 * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		String username = (String) token.getPrincipal();
		System.out.println(username);
		//sysuser.put("accountName", "" + username + "");
		SysUser sysuser=new SysUser();
		sysuser.setAccountname(username);
		try {
			List<SysUser> users = userMapper.findByNames(sysuser);
			if (users.size() != 0) {
				if ("2".equals(users.get(0).getLocked())) {
					throw new LockedAccountException(); // 帐号锁定
				}
				// 从数据库查询出来的账号名和密码,与用户输入的账号和密码对比
				// 当用户执行登录时,在方法处理上要实现user.login(token);
				// 然后会自动进入这个类进行认证
				// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, // 用户名
						users.get(0).getPassword(), // 密码
						ByteSource.Util.bytes(username + "" + users.get(0).getCredentialssalt()),// salt=username+salt
						getName() // realm name
				);
				String dd=users.get(0).getPassword();
				ByteSource bytsource=ByteSource.Util.bytes(username + "" + users.get(0).getCredentialssalt());
				
				 // 读取用户的url和角色				
				List<String> urllst=userRoleMapper.selectUrlListByUserId(users.get(0).getId());
				urllst.removeAll(Collections.singleton(""));
				urllst.removeAll(Collections.singleton(null));
				List<String> rolslst=userRoleMapper.selectRoleNameListByUserId(users.get(0).getId());
				rolslst.removeAll(Collections.singleton(""));
				rolslst.removeAll(Collections.singleton(null));
				
				// 当验证都通过后，把用户信息放在session里
				Session session = SecurityUtils.getSubject().getSession();
				session.setAttribute("userSession", users.get(0));
				session.setAttribute("userSessionId", users.get(0).getId());
				session.setAttribute("userRoles", org.apache.commons.lang.StringUtils.join(rolslst,","));
				///查找角色所有部门权限
			    List<Integer>  departList =  	sysDepartRoleMapper.selectDepartByRoles(users.get(0).getId());
			    String strDepart =   departList.toString();
			    strDepart=strDepart.substring(1,strDepart.length()-1);//去年两边方括号
			    if(null != users.get(0).getOrganizationId() && strDepart.length()>0 )
			    strDepart = strDepart+","+users.get(0).getOrganizationId() ; //将用户本身所属部门也存入session
			    
			    if(null != users.get(0).getOrganizationId() && strDepart.length()>=0 )//如果角色还没有部门，只加用户部门
				    strDepart = ""+users.get(0).getOrganizationId() ; //将用户本身所属部门也存入session
			    session.setAttribute("userDeparts",strDepart);//将角色部门存入session
			    Set<String> urls=new HashSet<String>(urllst);				
				Set<String> roles=new HashSet<String>(rolslst);		
							
				//Set<String> roles=new HashSet<String>();					
				//roles.add("admin");	
				ShiroUser shiroUser = new ShiroUser(users.get(0).getId(), username, username, urls);
		        shiroUser.setRoles(roles);
		        SimpleAuthenticationInfo auth=new SimpleAuthenticationInfo(shiroUser, users.get(0).getPassword(),ByteSource.Util.bytes(username + "" + users.get(0).getCredentialssalt()), getName());
		        
		        // 认证缓存信息		       
		        return auth;
				//return authenticationInfo;
			} else {
				throw new UnknownAccountException();// 没找到帐号
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	/**
     * 更新用户授权信息缓存.
     */
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
     * 更新用户信息缓存.
     */
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	/**
	 * 清除用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	/**
	 * 清除用户信息缓存.
	 */
	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}
	
	/**
	 * 清空所有缓存
	 */
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}


	/**
	 * 清空所有认证缓存
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}