package com.wxy.mapper;

import com.wxy.model.BokList;

public interface BokListMapper extends BaseMapper<BokList>{
    int insert(BokList record);

    int insertSelective(BokList record);
}