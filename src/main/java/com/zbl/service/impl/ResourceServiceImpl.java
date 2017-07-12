package com.zbl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.zbl.dao.SysResourceMapper;
import com.zbl.dao.SysRoleMapper;
import com.zbl.dao.SysUserRoleMapper;
import com.zbl.entity.SysResource;
import com.zbl.service.IResourceService;
import com.zbl.shiro.ShiroUser;
import com.zbl.util.Tree;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


/**
 * Resource 表数据服务层接口实现类
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements IResourceService {
    private static final int RESOURCE_MENU = 0; // 菜单
    private static final String TAG = "IResourceService";


    private SysResourceMapper resourceMapper;

    private SysUserRoleMapper userRoleMapper;

    private SysRoleMapper roleMapper;

    protected Logger logger = Logger.getLogger(ResourceServiceImpl.class);

    @Autowired
    public void setResourceMapper(SysResourceMapper resourceMapper) {
        this.resourceMapper = resourceMapper;
    }

    @Autowired
    public void setUserRoleMapper(SysUserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Autowired
    public void setRoleMapper(SysRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    // @Override
    public List<SysResource> selectAll() {
        logger.error(TAG + "：" + "selectAll");
        try {
            EntityWrapper<SysResource> wrapper = new EntityWrapper<SysResource>();
            wrapper.orderBy("seq");
            return resourceMapper.selectList(wrapper);
        } catch (Exception e) {
            logger.error(TAG + "：" + "selectAll异常" + StringEscapeUtils.escapeSql(e.getMessage()));
        }

        return null;
    }

    public List<SysResource> selectByType(Integer type) {
        logger.error(TAG + "：" + "selectByType");
        try {
            EntityWrapper<SysResource> wrapper = new EntityWrapper<SysResource>();
            SysResource resource = new SysResource();
            wrapper.setEntity(resource);
            wrapper.addFilter("resource_type = {0}", type);
            wrapper.orderBy("seq");
            return resourceMapper.selectList(wrapper);
        } catch (Exception e) {
            logger.error(TAG + "：" + "selectByType异常" + StringEscapeUtils.escapeSql(e.getMessage()));
        }
        return null;
    }

    // @Override
    public List<Tree> selectAllMenu() {
        logger.error(TAG + "：" + "selectAllMenu");
        try {
            List<Tree> trees = new ArrayList<Tree>();
            // 查询所有菜单
            List<SysResource> resources = this.selectByType(RESOURCE_MENU);
            if (resources == null) {
                return trees;
            }
            for (SysResource resource : resources) {
                Tree tree = new Tree();
                tree.setId(resource.getId());
                tree.setPid(resource.getPid());
                tree.setText(resource.getName());
                tree.setIconCls(resource.getIcon());
                tree.setAttributes(resource.getUrl());
                trees.add(tree);
            }
            return trees;
        } catch (Exception e) {
            logger.error(TAG + "：" + "selectAllMenu异常" + StringEscapeUtils.escapeSql(e.getMessage()));
        }

        return null;
    }

    // @Override
    public List<Tree> selectAllTree() {
        logger.error(TAG + "：" + "selectAllTree");
        try {
            // 获取所有的资源 tree形式，展示
            List<Tree> trees = new ArrayList<Tree>();
            List<SysResource> resources = this.selectAll();
            if (resources == null) {
                return trees;
            }
            for (SysResource resource : resources) {
                Tree tree = new Tree();
                tree.setId(resource.getId());
                tree.setPid(resource.getPid());
                tree.setText(resource.getName());
                tree.setIconCls(resource.getIcon());
                tree.setAttributes(resource.getUrl());
                trees.add(tree);
            }
            return trees;
        } catch (Exception e) {
            logger.error(TAG + "：" + "selectAllTree异常" + StringEscapeUtils.escapeSql(e.getMessage()));
        }

        return null;
    }

    // @Override
    public List<Tree> selectTree(ShiroUser shiroUser) {
        logger.error(TAG + "：" + "selectTree");
        try {
            List<Tree> trees = new ArrayList<Tree>();
            // shiro中缓存的用户角色
            Set<String> roles = shiroUser.getRoles();
            if (roles == null) {
                return trees;
            }
            // 如果有超级管理员权限
            if (roles.contains("admin")) {
                List<SysResource> resourceList = this.selectByType(RESOURCE_MENU);
                if (resourceList == null) {
                    return trees;
                }
                for (SysResource resource : resourceList) {
                    Tree tree = new Tree();
                    tree.setId(resource.getId());
                    tree.setPid(resource.getPid());
                    tree.setText(resource.getName());
                    tree.setIconCls(resource.getIcon());
                    tree.setAttributes(resource.getUrl());
                    tree.setOpenMode(resource.getOpenMode());
                    trees.add(tree);
                }
                return trees;
            }
            // 普通用户
            List<Integer> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
            if (roleIdList == null) {
                return trees;
            }
            List<SysResource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
            if (resourceLists == null) {
                return trees;
            }
            for (SysResource resource : resourceLists) {
                Tree tree = new Tree();
                tree.setId(resource.getId());
                tree.setPid(resource.getPid());
                tree.setText(resource.getName());
                tree.setIconCls(resource.getIcon());
                tree.setAttributes(resource.getUrl());
                tree.setOpenMode(resource.getOpenMode());
                trees.add(tree);
            }
            return trees;
        } catch (Exception e) {
            logger.error(TAG + "：" + "selectTree异常" + StringEscapeUtils.escapeSql(e.getMessage()));
        }

        return null;
    }
}