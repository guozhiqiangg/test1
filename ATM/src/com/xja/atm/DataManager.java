package com.xja.atm;

import java.io.*;

import static com.xja.atm.Bank.userList;

/**
 * @author 郭志强
 */
public class DataManager {
    User user = new User();


    public void readFile(String user) {
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("d:/user.txt"));

            User us=(User)ois.readObject();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }
    public void writeFile(String user) {
        try {
            //写对象流的对象
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("d:/user.txt"));

            oos.writeObject(userList);

            oos.close();                        //关闭文件流
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}
