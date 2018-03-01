package com.wxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.wxy.mapper.RecordMapper;
import com.wxy.model.Record;
import com.wxy.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-12 10:30
 * @desc ${DESCRIPTION}
 **/
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<Record> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return recordMapper.selectAll();
    }

    @Override
    public int insertOne(Record record) {
        return recordMapper.insert(record);
    }

    @Override
    public int deleteOneByPrimaryKey(Integer userid) {
        return 0;
    }

    @Override
    public int updateOne(Record record) {
        return 0;
    }

    @Override
    public Record selectOneByPrimaryKey(Integer userid) {
        return null;
    }

    @Override
    public boolean exist(int id) {
        return recordMapper.exist(id) == 1;
    }
}
