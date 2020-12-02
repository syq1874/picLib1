package com.yc.piclib.dao.impl;

import com.yc.piclib.dao.MisBaseMapper;
import com.yc.piclib.entity.Pic;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface PicMapper extends MisBaseMapper<Pic> {
}
