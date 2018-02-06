package com.renjunyou.ssmdemo.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.renjunyou.ssmdemo.beans.User;

/** 
 * 实例实现利用jackson实现实体对象与json字符串的互相转换 
 * @author liangming.deng 
 * 
 */  
public class JacksonDemo {  
    public static void main(String[] args){  
          
        User user1 = new User(1, "小明", 11);
        User user2 = new User(2, "李梅", 22);  
        User user3 = new User(3, "陈刚", 33);  
          
          
        List<User> userList = new ArrayList<User>();  
        userList.add(user1);  
        userList.add(user2);  
        userList.add(user3);  
          
        //对象转json  
        String userBeanToJson = JacksonUtil.toJSon(user1);  
        System.out.println("userBean to json:" + userBeanToJson);  
          
        //json转字符串  
        User jsonToUserBean = JacksonUtil.readValue(userBeanToJson, User.class);  
        System.out.println("json to UserBean" + jsonToUserBean.toString());  
          
        //List 转json字符串  
        String listToJson = JacksonUtil.toJSon(userList);  
        System.out.println("list to json:" + listToJson);  
          
        //数组json转 List  
        List<User> jsonToUserBeans = JacksonUtil.readValue(listToJson, new TypeReference<List<User>>() {  
        });  
        String userBeanString = "";  
        for (User userBean : jsonToUserBeans) {  
            userBeanString += userBean.toString() + "\n";  
        }  
        System.out.println("json to userBeans:" + userBeanString);  
    }  
      
      
}  
