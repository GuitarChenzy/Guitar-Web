package com.cogu.shop.controller.admin;

import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.entity.vo.Turl;
import com.cogu.shop.service.UrlService;
import com.cogu.shop.tools.PageBean;
import com.cogu.shop.tools.UrlCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 15:13
 * @Description:
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/getAll")
    public ModelMap getRole(@RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "rows", required = false) Integer size) {
        ModelMap modelMap = new ModelMap();
        PageBean pageBean = new PageBean(page, size);
        List<Turl> turlList = urlService.findTurl(pageBean.getPage(), pageBean.getPageSize());
        Integer total = urlService.findCount();
        if (turlList != null && turlList.size() > 0) {
            modelMap.addAttribute("rows", turlList);
            modelMap.addAttribute("total", total);
        }
        return modelMap;
    }

    @PostMapping("/add")
    public RestResult<Turl> addTurl(@ModelAttribute("Turl") Turl turl,
                                    @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/url/add")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = urlService.saveTurl(turl);
        if (result == 1) {
            return new RestResult<>(200, turl, "添加成功");
        } else {
            return new RestResult<>(500, null, "添加失败");
        }
    }

    @PostMapping("/update")
    public RestResult<Integer> updateUrl(@ModelAttribute("Turl") Turl turl,
                                         @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/url/update")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = urlService.updateTurl(turl);
        if (result == 1) {
            return new RestResult<>(200, null, "修改成功");
        } else {
            return new RestResult<>(500, null, "修改失败");
        }
    }

    @PostMapping("/delete")
    public RestResult<Integer> deleteUrl(@RequestParam("fid") Integer fid,
                                         @SessionAttribute("url") List<String> url) {
        if (!UrlCompare.findUrl(url, "/url/delete")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = urlService.deleteTurl(fid);
        if (result == 1) {
            return new RestResult<>(200, null, "删除成功");
        } else {
            return new RestResult<>(500, null, "删除失败");
        }
    }

}
