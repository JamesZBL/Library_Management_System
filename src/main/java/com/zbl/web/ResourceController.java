package com.zbl.web;

import java.util.Date;

import com.zbl.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbl.entity.SysResource;
import com.zbl.shiro.ShiroUser;
import com.zbl.service.IResourceService;

/**
 * @description：资源管理
 * @author：
 * @date：
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;


    /**
     * 菜单树
     *
     * @return
     */
    @RequestMapping(value="/tree",method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        ShiroUser shiroUser = getShiroUser();
        return resourceService.selectTree(shiroUser);
    }

    /**
     * 资源管理页
     *
     * @return
     */
    @RequestMapping(value="/manager",method = RequestMethod.GET)
    public String manager() {
        return "admin/resource";
    }

    /**
     * 资源管理列表
     *
     * @return
     */
    @RequestMapping(value="/treeGrid",method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid() {
        return resourceService.selectAll();
    }

    /**
     * 添加资源页
     *
     * @return
     */
    @RequestMapping(value="/addPage",method = RequestMethod.GET)
    public String addPage() {
        return "admin/resourceAdd";
    }

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(SysResource resource) {
        resource.setCreateTime(new Date());
        // 选择菜单时将openMode设置为null
        Integer type = resource.getResourceType();
        if (null != type && type == 0) {
            resource.setOpenMode(null);
        }
        resourceService.insert(resource);
        return renderSuccess("添加成功！");
    }

    /**
     * 查询所有的菜单
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public Object allMenu() {
        return resourceService.selectAllMenu();
    }

    /**
     * 查询所有的资源tree
     */
    @RequestMapping("/allTrees")
    @ResponseBody
    public Object allTree() {
        return resourceService.selectAllTree();
    }

    /**
     * 编辑资源页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
    	SysResource resource = resourceService.selectById(id);
        model.addAttribute("resource", resource);
        return "admin/resourceEdit";
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysResource resource) {
        // 选择菜单时将openMode设置为null
        Integer type = resource.getResourceType();
        if (null != type && type == 0) {
            resource.setOpenMode(null);
        }
        resourceService.updateById(resource);
        return renderSuccess("编辑成功！");
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        resourceService.deleteById(id);
        return renderSuccess("删除成功！");
    }

}
