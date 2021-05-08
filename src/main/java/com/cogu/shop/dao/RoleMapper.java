package com.cogu.shop.dao;

import com.cogu.shop.dao.provider.RoleProvider;
import com.cogu.shop.entity.vo.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 10:33
 * @Description:
 */
@Mapper
@Component
public interface RoleMapper {

    @Select("select * from role where rid in " +
            "(select rid from admin_role where aid = #{aid} and rstate = 1)")
    List<Role> findRoleByAid(@Param("aid") Integer aid);

    @Select("select fid from role_function where rid = #{rid}")
    List<Integer> findRoleByRid(@Param("rid") Integer rid);

    @Select("select * from role")
    List<Role> findAllRole();

    @Select("select count(*) from role")
    Integer findCount();

    @Select("select * from role limit #{startpage},#{gnumber}")
    List<Role> findAllRoleWithLimit(@Param("startpage") Integer startpage, @Param("gnumber") Integer gnumber);

    @InsertProvider(type = RoleProvider.class, method = "saveRoleUrl")
    Integer saveRoleUrl(Integer rid, Integer... fid);

    @Insert("insert into role (rolename,rdesc,rchild,publictime) " +
            "values(#{rolename},#{rdesc}," +
            "#{rchild},now())")
    @Options(useGeneratedKeys = true, keyProperty = "rid", keyColumn = "rid")
    void saveRole(Role role);

    @UpdateProvider(type = RoleProvider.class, method = "updateRole")
    Integer updateRole(Role role);

    @Update("update role set rchild = 0 where rchild = #{rid}")
    Integer updateRchild(@Param("rid") Integer rid);

    @Delete("delete from role where rid = #{rid}")
    Integer deleteRole(@Param("rid") Integer rid);

    @Delete("delete from role_function where rid = #{rid}")
    Integer deleteRoleUrl(@Param("rid") Integer rid);

    @Delete("delete from role_function where rid = #{rchild}")
    Integer deleteRoleByRchild(@Param("rchild") Integer rchild);

    @Delete("delete from admin_role where aid = #{aid}")
    Integer deleteRoleByAdmin(@Param("aid") Integer aid);
}
