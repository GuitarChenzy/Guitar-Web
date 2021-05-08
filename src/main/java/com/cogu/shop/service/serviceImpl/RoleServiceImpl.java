package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.controller.entity.Tree;
import com.cogu.shop.dao.RoleMapper;
import com.cogu.shop.entity.vo.Role;
import com.cogu.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 16:42
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Tree> findRole() {
        List<Role> roleList = roleMapper.findAllRole();
        List<Tree> treeList = new ArrayList<>();
        for (Role role : roleList) {
            Tree tree = new Tree();
            tree.setId(role.getRid());
            tree.setText(role.getRolename());
            treeList.add(tree);
        }
        return treeList;
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }

    @Override
    public List<Role> findRole(Integer page, Integer size) {
        return roleMapper.findAllRoleWithLimit(page, size);
    }

    @Override
    public Integer findCount() {
        return roleMapper.findCount();
    }

    @Override
    @Transactional
    public Integer saveRole(Role role, Integer... fid) {
        //先判断是否有下级角色
        //有：取出下级角色的fid，和fid做差集，插入fid
        //无：直接插入fid
        Integer result = 0;
        roleMapper.saveRole(role);
        if (role.getRchild() != null && role.getRchild() != 0) {
            Integer[] fid_new_List = new Integer[fid.length];
            List<Integer> fidList = roleMapper.findRoleByRid(role.getRchild());
            if (fidList != null && fidList.size() > 0) {
                for (int i = 0, total = fid.length; i < total; i++) {
                    if (fidList.contains(fid[i])) {
                        System.out.println(" cun zai :" + fid[i]);
                    } else {
                        fid_new_List[i] = fid[i];
                    }
                }
            }
            roleMapper.saveRoleUrl(role.getRid(), fid_new_List);
            result = 1;
        } else {
            roleMapper.saveRoleUrl(role.getRid(), fid);
            result = 1;
        }
        return result;
    }

    @Override
    @Transactional
    public Integer updateRole(Role role, Integer... fid) {
        roleMapper.deleteRoleUrl(role.getRid()); //先删除所有旧的资源
        roleMapper.saveRoleUrl(role.getRid(), fid); //再添加所有新的资源
        return roleMapper.updateRole(role);//最后更新角色
    }

    @Override
    @Transactional
    public Integer deleteRole(Integer rid, Integer rchild) {
       /* if (rchild != null && rchild != 0) {
            roleMapper.deleteRoleByRchild(rchild); //有下级的时候先删掉下级url资源
        } else {*/
            roleMapper.updateRchild(rid);
        //}
        return roleMapper.deleteRole(rid);
    }
}
