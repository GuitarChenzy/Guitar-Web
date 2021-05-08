package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.controller.entity.Tree;
import com.cogu.shop.dao.UrlMapper;
import com.cogu.shop.entity.vo.Turl;
import com.cogu.shop.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/7/5 22:33
 * @Description:
 */
@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlMapper urlMapper;

    @Override
    public List<Tree> findUrlTree() {
        List<Turl> turlList = urlMapper.findAllTurl();
        List<Tree> treeList = new ArrayList<>();
        for (Turl turl : turlList) {
            Tree tree = new Tree();
            tree.setId(turl.getFid());
            tree.setText(turl.getFname());
            treeList.add(tree);
        }
        return treeList;
    }

    @Override
    public List<Turl> findTurl(Integer page, Integer size) {
        return urlMapper.findAllTurlWithLimit(page, size);
    }

    @Override
    public Integer findCount() {
        return urlMapper.findCount();
    }

    @Override
    @Transactional
    public Integer saveTurl(Turl turl) {
        return urlMapper.saveTurl(turl);
    }

    @Override
    public Integer updateTurl(Turl turl) {
        return urlMapper.updateTurl(turl);
    }

    @Override
    @Transactional
    public Integer deleteTurl(Integer fid) {
        urlMapper.deleteTurlByFid(fid); //删除关联表的fid
        return urlMapper.deleteTurl(fid); //删除fid
    }
}
