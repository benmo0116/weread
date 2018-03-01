package com.wxy.mapper;

import com.wxy.model.Record;

public interface RecordMapper extends BaseMapper<Record>{
    int insert(Record record);

    int insertSelective(Record record);
}