package com.xja.atm;

import java.util.ArrayList;
import java.util.List;

/**
 * main方法
 * @author 郭志强
 */
public class Bank {
    //用户
    User user = new User();
    //用户集合
    public  static List<User> userList = new ArrayList<>();



    public static void main(String[] args) {
        //读取原有用户信息
        DataManager dataManager = new DataManager();
        dataManager.readFile("user");
        //调用登录菜单
        View view = new View();
        view.menu1();
    }
}
