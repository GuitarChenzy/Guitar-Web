package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.dao.AdminMapper;
import com.cogu.shop.dao.RoleMapper;
import com.cogu.shop.dao.UrlMapper;
import com.cogu.shop.entity.vo.Admin;
import com.cogu.shop.entity.vo.Role;
import com.cogu.shop.entity.vo.Turl;
import com.cogu.shop.service.AdminService;
import com.cogu.shop.tools.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UrlMapper urlMapper;

    @Override
    public Admin findAdmin(String username, String password) {
        String password2 = MD5Utils.EncoderByMd5(password);
        Admin admin = adminMapper.findAdmin(username, password2);
        if (admin != null) {
            adminMapper.updateLogTime(admin.getAid());//更新登录时间
            //获取角色信息
            List<Role> roleList = roleMapper.findRoleByAid(admin.getAid());
            //获取url路径
            for (Role role : roleList) {
                //获取子身份的url
                if (role.getRchild() != 0) {
                    List<Turl> turlList = urlMapper.findTurlByrid(role.getRchild());
                    role.setTurlList(turlList);
                }
                //获取身份的url
                List<Turl> turlList2 = urlMapper.findTurlByrid(role.getRid());
                //将url保存进role中
                if (role.getTurlList() == null) {
                    role.setTurlList(new ArrayList<>());
                }
                role.getTurlList().addAll(turlList2);
                System.out.println(role.getTurlList());
            }
            //将角色保存进用户中
            admin.setRoleList(roleList);
        }
        return admin;
    }

    @Override
    public Integer findCount() {
        return adminMapper.findCount();
    }

    @Override
    public List<Admin> findAdmin(Integer page, Integer size) {
        List<Admin> adminList = adminMapper.findAdminWithLimit(page, size);
        for (Admin admin : adminList) {
            List<Role> roleList = roleMapper.findRoleByAid(admin.getAid());
            admin.setRoleList(roleList);
        }
        return adminList;
    }

    @Override
    @Transactional
    public Integer saveAdmin(Admin admin, Integer... rid) {
        if (adminMapper.nameCheck(admin.getUsername()) > 0) {
            return 2;//账号重复
        }
        admin.setPassword(MD5Utils.EncoderByMd5(admin.getPassword()));
        adminMapper.saveAdmin(admin);
        if (adminMapper.saveAdminRole(admin.getAid(), rid) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    @Transactional
    public Integer updateAdmin(Admin admin, Integer... rid) {
        admin.setPassword(MD5Utils.EncoderByMd5(admin.getPassword()));
        adminMapper.deleteAdminRole(admin.getAid());
        adminMapper.saveAdminRole(admin.getAid(), rid);
        return adminMapper.updateAdmin(admin);
    }

    @Override
    @Transactional
    public Integer deleteAdmin(Integer aid) {
        roleMapper.deleteRoleByAdmin(aid);
        return adminMapper.deleteAdmin(aid);
    }
}
