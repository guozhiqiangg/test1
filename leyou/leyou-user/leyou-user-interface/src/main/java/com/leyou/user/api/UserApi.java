package com.leyou.user.api;

import com.leyou.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录接口
 */
public interface UserApi {

    /**
     * 按用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    User queryUser(@RequestParam("username") String username, @RequestParam("password") String password);

}
