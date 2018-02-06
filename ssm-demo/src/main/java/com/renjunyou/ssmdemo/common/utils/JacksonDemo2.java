package com.renjunyou.ssmdemo.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.renjunyou.ssmdemo.beans.Dept;

/** 
 * 实例实现利用jackson实现实体对象与json字符串的互相转换 
 * @author liangming.deng 
 * 
 */  
public class JacksonDemo2 {  
    public static void main(String[] args){  
          

    	Dept dept1 = new Dept(1,"开发部");
    	dept1.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
    	Dept dept2 = new Dept(2,"测试部");
    	dept2.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
    	Dept dept3 = new Dept(3,"市场部");
    	dept3.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
          
          
        List<Dept> deptList = new ArrayList<Dept>();  
        deptList.add(dept1);  
        deptList.add(dept2);  
        deptList.add(dept3);  
          
        //对象转json  
        String deptBeanToJson = JacksonUtil.toJSon(dept1);  
        System.out.println("deptBean to json:" + deptBeanToJson);  
          
        //json转字符串  
        Dept jsonToDeptBean = JacksonUtil.readValue(deptBeanToJson, Dept.class);  
        System.out.println("json to DeptBean" + jsonToDeptBean.toString());  
          
        //List 转json字符串  
        String listToJson = JacksonUtil.toJSon(deptList);  
        System.out.println("list to json:" + listToJson);  
          
        //数组json转 List  
        List<Dept> jsonToDeptBeans = JacksonUtil.readValue(listToJson, new TypeReference<List<Dept>>() {  
        });  
        String deptBeanString = "";  
        for (Dept deptBean : jsonToDeptBeans) {  
        	deptBeanString += deptBean.toString() + "\n";  
        }  
        System.out.println("json to deptBeans:" + deptBeanString);  
    }  
      
      
}  
