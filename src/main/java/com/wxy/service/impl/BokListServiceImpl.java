package com.wxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxy.mapper.BokListMapper;
import com.wxy.model.BokList;
import com.wxy.service.BokListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-15 09:12
 * @desc ${DESCRIPTION}
 **/
@Service
public class BokListServiceImpl implements BokListService {

    @Autowired
    BokListMapper bokListMapper;
    @Override
    public List<BokList> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return bokListMapper.selectAll();
    }

    @Override
    public int insertOne(BokList bokList) {
        return bokListMapper.insert(bokList);
    }

    @Override
    public int deleteOneByPrimaryKey(Integer userid) {
        return 0;
    }

    @Override
    public int updateOne(BokList bokList) {
        return 0;
    }

    @Override
    public BokList selectOneByPrimaryKey(Integer userid) {
        return null;
    }

    @Override
    public boolean exist(int id) {
        return bokListMapper.exist(id) == 1;
    }
}
