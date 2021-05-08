package com.cogu.shop.controller.admin;

import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.controller.entity.Tree;
import com.cogu.shop.entity.vo.Role;
import com.cogu.shop.service.RoleService;
import com.cogu.shop.service.UrlService;
import com.cogu.shop.tools.PageBean;
import com.cogu.shop.tools.UrlCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 9:35
 * @Description:
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UrlService urlService;

    @PostMapping("/getAll")
    public ModelMap getRole(@RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "rows", required = false) Integer size) {
        ModelMap modelMap = new ModelMap();
        PageBean pageBean = new PageBean(page, size);
        List<Role> roleList = roleService.findRole(pageBean.getPage(), pageBean.getPageSize());
        Integer total = roleService.findCount();
        if (roleList != null && roleList.size() > 0) {
            modelMap.addAttribute("rows", roleList);
            modelMap.addAttribute("total", total);
        }
        return modelMap;
    }

    @PostMapping("/get_parent")
    public RestResult<List<Role>> getAllRole() {
        List<Role> roleList = roleService.findAllRole();
        if (roleList != null && roleList.size() > 0) {
            return new RestResult<>(200, roleList, "获取成功");
        } else {
            return new RestResult<>(201, null, "类别为空");
        }
    }

    @PostMapping("/get_url")
    public RestResult<List<Tree>> getAllUrl() {
        List<Tree> treeList = urlService.findUrlTree();
        if (treeList != null && treeList.size() > 0) {
            return new RestResult<>(200, treeList, "获取成功");
        } else {
            return new RestResult<>(201, null, "类别为空");
        }
    }

    @PostMapping("/add")
    public RestResult<Role> addRole(@ModelAttribute("Role") Role role,
                                    @SessionAttribute("url") List<String> url,
                                    @RequestParam("url") Integer... fid) {
        if (!UrlCompare.findUrl(url, "/role/add")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = roleService.saveRole(role, fid);
        if (result == 1) {
            return new RestResult<>(200, role, "添加成功");
        } else {
            return new RestResult<>(500, null, "添加失败");
        }
    }

    @PostMapping("/update")
    public RestResult<Integer> updateRole(@ModelAttribute("Role") Role role,
                                          @SessionAttribute("url") List<String> url,
                                          @RequestParam("url") Integer... fid) {
        if (!UrlCompare.findUrl(url, "/role/update")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = roleService.updateRole(role, fid);
        if (result == 1) {
            return new RestResult<>(200, result, "修改成功");
        } else {
            return new RestResult<>(500, null, "修改失败");
        }
    }

    @PostMapping("/delete")
    public RestResult<Integer> deleteRole(@RequestParam("rid") Integer rid,
                                          @RequestParam("rchild") Integer rchild,
                                          @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/role/delete")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = roleService.deleteRole(rid, rchild);
        if (result == 1) {
            return new RestResult<>(200, result, "删除成功");
        } else {
            return new RestResult<>(500, null, "删除失败");
        }
    }

}
