package com.zbl.web;

import com.zbl.entity.SysRole;
import com.zbl.entity.SysUser;
import com.zbl.entity.vo.UserVo;
import com.zbl.service.IUserService;
import com.zbl.util.PageInfo;
import com.zbl.util.PasswordHelper;
import com.zbl.util.StringUtils;
import com.zbl.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：用户管理
 * @author：
 * @date：
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 用户管理页
     *
     * @return
     */  
    @RequestMapping(value="/manager",method = RequestMethod.GET)
    public String manager() {
        return "admin/user";
    }

    /**
     * 用户管理列表
     *
     * @param userVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */    
    @RequestMapping(value="/dataGrid",method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(userVo.getUsername())) {
            condition.put("name", userVo.getUsername());
        }
        if (userVo.getOrganizationId() != null) {
            condition.put("organizationId", userVo.getOrganizationId());
        }
        if (userVo.getCreatedateStart() != null) {
            condition.put("startTime", userVo.getCreatedateStart());
        }
        if (userVo.getCreatedateEnd() != null) {
            condition.put("endTime", userVo.getCreatedateEnd());
        }
        pageInfo.setCondition(condition);
        
        
        
        userService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加用户页
     *
     * @return
     */    
    @RequestMapping(value="/addPage",method = RequestMethod.GET)
    public String addPage() {
        return "admin/userAdd";
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */    
    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(UserVo userVo) {
        List<SysUser> list = userService.selectByLoginName(userVo);
        if (list != null && !list.isEmpty()) {
            return renderError("用户名已存在!");
        }
        if (StringUtils.isEmpty(userVo.getPassword())) {
        	userVo.setPassword("123456");
		}	
        PasswordHelper passwordHelper = new PasswordHelper();
		passwordHelper.encryptPassword(userVo);// 对密码进行加密
        //userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        
        userService.insertByVo(userVo);
        return renderSuccess("添加成功");
    }

    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */    
    @RequestMapping(value="/editPage",method = RequestMethod.GET)
    public String editPage(Model model, Integer id) {
        UserVo userVo = userService.selectVoById(id);
        List<SysRole> rolesList = userVo.getRolesList();
        List<Integer> ids = new ArrayList<Integer>();
        for (SysRole role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "admin/userEdit";
    }

    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(UserVo userVo) {
        List<SysUser> list = userService.selectByLoginName(userVo);
        if (list != null && !list.isEmpty()) {
            return renderError("用户名已存在!");
        }
        if (!StringUtils.isEmpty(userVo.getPassword())) {
        	 PasswordHelper passwordHelper = new PasswordHelper();
     		passwordHelper.encryptPassword(userVo);// 对密码进行加密
		}
		/*
        if (StringUtils.isNotBlank(userVo.getPassword())) {
            userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
        }*/
        userService.updateByVo(userVo);
        return renderSuccess("修改成功！");
    }

    /**
     * 修改密码页
     *
     * @return
     */    
    @RequestMapping(value="/editPwdPage",method = RequestMethod.GET)
    public String editPwdPage() {
        return "admin/userEditPwd";
    }

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(String oldPwd, String pwd) {
    	
        SysUser user = userService.selectById(getUserId()); 
        PasswordHelper passwordHelper = new PasswordHelper();
        if(!passwordHelper.IsPasswordMatched(oldPwd, user))
        {
        	 return renderError("老密码不正确!");
        }
        
        /*if (!user.getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
            return renderError("老密码不正确!");
        }*/
        
        if (!StringUtils.isEmpty(pwd)) {
        	user.setPassword(pwd);
    		passwordHelper.encryptPassword(user);// 对密码进行加密    		
    		userService.updateById(user);
    		//userService.updatePwdByUserId(getUserId(), DigestUtils.md5Hex(pwd));    		
    		return renderSuccess("密码修改成功！");
        }
        else
        {
        	return renderError("密码不能为空!");
        }
        
        
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        userService.deleteUserById(id);
        return renderSuccess("删除成功！");
    }
}
