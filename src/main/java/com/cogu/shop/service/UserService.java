package com.cogu.shop.service;

import com.cogu.shop.entity.vo.User;

import java.util.List;

/**
 * Created by Cogu on 2018/6/10.
 */
public interface UserService {

    /**
     * 通过uid查找user
     *
     * @param uid 用户id
     * @return 用户对象
     */
    User findUserByUid(Integer uid);

    /**
     * 查询所有用户
     *
     * @return 用户集合
     */
    List<User> findAllUser();

    /**
     * 通过username检查是否重复
     *
     * @param username 账号名
     * @return 1存在0不存在
     */
    Integer findUserByUsername(String username);

    /**
     * 保存user
     *
     * @param user 用户对象
     * @return 1成功0失败
     */
    Integer saveUser(User user);

    /**
     * 登陆验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户或null
     */
    User findUser(String username, String password);

    /**
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 验证邮箱是否重复
     *
     * @param email 邮箱地址
     * @return 1重复0不重复
     */
    Integer findEmailCount(String email);

    /**
     * 修改密码
     * @param email 邮箱地址
     * @param password 新密码
     * @return 1成功0失败
     */
    Integer updatePassword(String email, String password);

}
