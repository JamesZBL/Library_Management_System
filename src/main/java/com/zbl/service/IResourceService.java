package com.zbl.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zbl.entity.SysResource;
import com.zbl.shiro.ShiroUser;
import com.zbl.util.Tree;


/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResourceService extends IService<SysResource> {

    List<SysResource> selectAll();

    List<Tree> selectAllMenu();

    List<Tree> selectAllTree();

    List<Tree> selectTree(ShiroUser shiroUser);

}