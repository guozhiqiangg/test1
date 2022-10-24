package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BrandMapper extends Mapper<Brand> {

    @Insert("insert into tb_category_brand(category_id,brand_id) values (#{cid},#{bid})")
    int insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    @Update("update tb_brand set name=#{name},image=#{image},letter=#{letter} where id=#{id} ")
    int updateBrand(Brand brand);

    @Delete("delete from tb_category_brand where brand_id=#{bid}")
    int deleteCategoryAndBrand(@Param("bid") Long bid);

@Select("select b.*from tb_brand b left join tb_category_brand cb on b.id=cb.brand_id where cb.category_id=#{cid}  ")
    List<Brand> queryByCategoryId(@Param("cid") Long cid);
}