package com.leyou.item.mapper;

import com.leyou.item.pojo.Spu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface SpuMapper extends Mapper<Spu> {

    @Update("update tb_spu set valid=#{valid} where id=#{id}")
    void updateValid(Long id, Boolean valid);
}
