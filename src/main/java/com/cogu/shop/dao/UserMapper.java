package com.cogu.shop.dao;

import com.cogu.shop.entity.vo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Cogu on 2018/6/10.
 */
@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where uid = #{uid}")
    User findUserByUid(@Param("uid") Integer uid);

    @Select("select * from user")
    List<User> findAllUser();

    @Select("select count(*) " +
            "from user " +
            "where username = #{username}")
    Integer findUserByUsername(@Param("username") String username);

    @Select("select * from user " +
            "where username = #{username} and password = #{password}")
    User findUser(@Param("username") String username, @Param("password") String password);

    @Insert("insert into user " +
            "(username,realname,password,sex,email,ucoin,adress,registertime) " +
            "values(#{user.username},#{user.realname},#{user.password},#{user.sex}," +
            "#{user.email},#{user.ucoin},#{user.adress},now())")
    Integer saveUser(@Param("user") User user) throws RuntimeException;

    @Update("update user set logintime = now() " +
            "where uid = #{uid}")
    void updateUserLog(@Param("uid") Integer uid) throws RuntimeException;

    @Update("update user set realname = #{user.realname},sex = #{user.sex}," +
            "email = #{user.email},adress = #{user.adress} " +
            "where uid = #{user.uid}")
    Integer updateUser(@Param("user") User user) throws RuntimeException;

    @Update("update user set password = #{password} where email = #{email}")
    Integer updateUserPassword(@Param("email") String email, @Param("password") String password);

    @Select("select count(*) from user where email = #{email}")
    Integer findEmailCount(@Param("email") String email);


}
