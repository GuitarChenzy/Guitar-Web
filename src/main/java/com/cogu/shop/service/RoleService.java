package com.cogu.shop.service;

import com.cogu.shop.controller.entity.Tree;
import com.cogu.shop.entity.vo.Role;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 16:39
 * @Description:
 */
public interface RoleService {

    /**
     * 查询所有角色
     *
     * @return 角色集合
     */
    List<Tree> findRole();

    /**
     *
     * @return
     */
    List<Role> findAllRole();

    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 数值
     * @return 角色集合
     */
    List<Role> findRole(Integer page, Integer size);

    /**
     * 获取角色总数
     *
     * @return
     */
    Integer findCount();

    /**
     * 保存角色
     * @param role 角色对象
     * @param fid 资源路径
     * @return 1成功0失败
     */
    Integer saveRole(Role role,Integer... fid);

    /**
     * 更新角色
     * @param role 角色对象
     * @param fid 资源路径
     * @return 1成功0失败
     */
    Integer updateRole(Role role,Integer... fid);

    /**
     * 删除
     *
     * @param rid 角色id
     * @return 1成功0失败
     */
    Integer deleteRole(Integer rid,Integer rchild);

}

