package com.cogu.shop.dao;

import com.cogu.shop.dao.provider.AdminProvider;
import com.cogu.shop.entity.vo.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Cogu on 2018/6/20.
 */
@Mapper
@Component
public interface AdminMapper {

    @Select("select count(*) from admin where username  =#{username}")
    Integer nameCheck(@Param("username") String username);

    @Select("select * from admin where username = #{username} and password = #{password}")
    Admin findAdmin(@Param("username") String username, @Param("password") String password);

    @Select("select * from admin limit #{page},#{size}")
    List<Admin> findAdminWithLimit(@Param("page") Integer page, @Param("size") Integer size);

    @Select("select count(*) from admin")
    Integer findCount();

    @Insert("insert into admin (username,rolename,password,regtime) " +
            "values(#{username},#{rolename}," +
            "#{password},now())")
    @Options(useGeneratedKeys = true, keyProperty = "aid", keyColumn = "aid")
    void saveAdmin(Admin admin);

    @UpdateProvider(type = AdminProvider.class, method = "updateAdmin")
    Integer updateAdmin(Admin admin);

    @InsertProvider(type = AdminProvider.class, method = "saveAdminRole")
    Integer saveAdminRole(Integer aid, Integer... rid);

    @Update("update admin set logtime = now() where aid = #{aid}")
    Integer updateLogTime(@Param("aid") Integer aid);

    @Update("update admin_role set rstate = #{rstate}")
    Integer updateState(@Param("rstate") Integer rstate, @Param("aid") Integer aid, @Param("rid") Integer rid);

    @Delete("delete from admin_role where aid = #{aid}")
    Integer deleteAdminRole(@Param("aid") Integer aid);

    @Delete("delete from admin where aid = #{aid}")
    Integer deleteAdmin(@Param("aid") Integer aid);

}
