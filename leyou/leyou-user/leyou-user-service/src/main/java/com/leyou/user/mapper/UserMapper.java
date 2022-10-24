package com.leyou.user.mapper;

import com.leyou.user.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {
}