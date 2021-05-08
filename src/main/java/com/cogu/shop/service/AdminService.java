package com.cogu.shop.service;

import com.cogu.shop.entity.vo.Admin;

import java.util.List;

/**
 * Created by Cogu on 2018/6/20.
 */
public interface AdminService {

    /**
     * 登陆验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户?NULL
     */
    Admin findAdmin(String username, String password);

    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 数值
     * @return 管理员集合
     */
    List<Admin> findAdmin(Integer page, Integer size);

    /**
     * 获取管理员总数
     *
     * @return
     */
    Integer findCount();

    /**
     * 保存
     *
     * @param admin 管理员
     * @return 1成功0失败2账号名重复
     */
    Integer saveAdmin(Admin admin, Integer... rid);

    /**
     * 更新
     *
     * @param admin 管理员
     * @param rid   身份
     * @return 1成功0失败
     */
    Integer updateAdmin(Admin admin, Integer... rid);

    /**
     * 删除
     *
     * @param aid 管理员id
     * @return 1成功0失败
     */
    Integer deleteAdmin(Integer aid);

}
