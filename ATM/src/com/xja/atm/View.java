package com.xja.atm;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author 郭志强
 */
public class View {
    ATM atm = new ATM();

    boolean tag = false;
    public void menu1() {
        System.out.println("\t\t\t欢迎使用中国建行ATM\t\t\t");
        System.out.println("1.登录");
        System.out.println("2.查询");
        System.out.println("3.注册");
        System.out.println("4.退出");

        do {

            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入功能序列号");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        atm.login();
                        break;
                    case 2:
                        atm.select();
                        break;
                    case 3:
                        atm.register();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        tag = true;
                        if (choice<1){
                            System.out.println("只能输入正整数");
                        }
                        if(choice>4){
                            System.out.println("只能输入正确的序号进行选择");
                        }
                }

            }catch(Exception e){
                tag = true;
                System.out.println("只能输入整数，且不能输入字母");
            }
        }while (tag);

    }

}
