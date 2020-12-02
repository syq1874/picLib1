package com.yc.piclib.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.piclib.dao.impl.PicMapper;
import com.yc.piclib.domain.PageDomain;
import com.yc.piclib.domain.PicDomain;
import com.yc.piclib.entity.Pic;
import com.yc.piclib.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PicServiceImpl implements PicService {

    @Autowired(required = false)
    private PicMapper picMapper;


    @Transactional(readOnly = true)
    @Override
    public List<PicDomain> list() {
        //2. 缓存没有，则到数据库查
        List<Pic> list = picMapper.selectAll();
        //将实体类转为   domain
        List<PicDomain> r = new ArrayList<PicDomain>();
        for (Pic p : list) {   //  BeanUtils.copyBean(source, destination);
            PicDomain pd = new PicDomain(p.getId(), p.getPath(), p.getDescription());
            r.add(pd);
        }
        return r;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDomain<PicDomain> listByPage(PicDomain picDomain) {
        Example example = new Example(Pic.class);   //条件
        //分页条件设置
        PageHelper.startPage(picDomain.getPage(), picDomain.getPageSize());
        //排序条件
        example.setOrderByClause("id desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(picDomain.getDescription())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("description", "%" + picDomain.getDescription() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<Pic> pageInfo = new PageInfo<Pic>(picMapper.selectByExample(example));


        PageDomain<PicDomain> pageDomain = new PageDomain<PicDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(picDomain.getPageSize());
        //List<Pic> list = picMapper.selectByExample(example);
        List<PicDomain> r = new ArrayList<PicDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (Pic p : pageInfo.getList()) {
                PicDomain pd = new PicDomain(p.getId(), p.getPath(), p.getDescription());
                r.add(pd);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }

    @Override
    public void save(PicDomain picDomain) {
        Pic pic = new Pic();
        pic.setPath(picDomain.getPath());
        pic.setDescription(picDomain.getDescription());
        this.picMapper.insert(pic);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到pic中
        //pic中的id已经获取到
        //关键:
        picDomain.setId(pic.getId());
    }

    @Override
    public void delete(Integer id) {
        this.picMapper.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PicDomain findOne(Integer id) {
        Pic pic = this.picMapper.selectByPrimaryKey(id);
        PicDomain picDomain = new PicDomain(pic.getId(), pic.getPath(), pic.getDescription());
        return picDomain;
    }


}
