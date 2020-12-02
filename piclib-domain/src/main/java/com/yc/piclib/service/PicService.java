package com.yc.piclib.service;

import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.PicDomain;

import java.util.List;

public interface PicService {

    //查询所有图片
    public List<PicDomain> list();

    /**
     * 分页查找
     *
     * @param PicDomain: page:第几页   pageSize:每页多少条
     *                   description: 描述（模糊查询)
     * @return
     */
    public PageDomain<PicDomain> listByPage(PicDomain PicDomain);

    /**
     * 保存新图片
     *
     * @param picDomain
     */
    public void save(PicDomain picDomain);

    /**
     * 删除图片
     *
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据id查图片详情(    图片的metadata )
     *
     * @param id
     * @return
     */
    public PicDomain findOne(Integer id);


}
