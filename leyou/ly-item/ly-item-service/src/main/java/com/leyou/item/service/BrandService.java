package com.leyou.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //开始分页
        PageHelper.startPage(page, rows);
        //过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + " " + (desc ? "DESC" : "ASC");
            example.setOrderByClause(orderByClause);
        }

        Page<Brand> pageInfo = (Page<Brand>)
                brandMapper.selectByExample(example);
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insertSelective(brand);
        cids.forEach(cid -> {
            this.brandMapper.insertCategoryAndBrand(cid, brand.getId());
        });
    }

    @Transactional
    public void updateBrand(Brand brand, List<Long> cids) {
        this.brandMapper.updateBrand(brand);
        this.brandMapper.deleteCategoryAndBrand(brand.getId());
        cids.forEach(cid -> {
            this.brandMapper.insertCategoryAndBrand(cid, brand.getId());
        });
    }

    public void deleteBrand(Long bid) {
        this.brandMapper.deleteCategoryAndBrand(bid);
        this.brandMapper.deleteByPrimaryKey(bid);
    }

    public List<Brand> queryByCid(Long cid) {
        return this.brandMapper.queryByCategoryId(cid);
    }

    public Brand queryBrandByBid(Long bid) {
     return    this.brandMapper.selectByPrimaryKey(bid);
    }
}
