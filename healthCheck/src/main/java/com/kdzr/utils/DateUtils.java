package com.kdzr.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	/**
	 * 获取格式化的时间
	 * 输出格式：2015-08-04 20:55:35
	 */
	public static String getFormatDate(Date date){
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateString = formatter.format(date);
	    return dateString;
	}
	
	/**
	 * 获取格式化的时间
	 * 输出格式：2015年08月04日
	 */
	public static String getFormatDate2(Date date){
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
	    String dateString = formatter.format(date);
	    return dateString;
	}
	
	/**
	 * 获取格式化的时间
	 * 输出格式：08月04日
	 */
	public static String getFormatDate3(Date date){
	    SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
	    String dateString = formatter.format(date);
	    return dateString;
	}
	
	
	/**
	 * 获取格式化的时间,前5小时或后2小时
	 * 输出格式：2015-08-04 20:55:35
	 * @param Date d  需要格式化的日期
	 * @param int f  -1表示向前5小时
	 */
	public static String getFormatDate3(Date d,int f){
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    if(f == -1){
	    	return df.format(new Date(d.getTime() - 30 * 60 * 1000));
	    }else{
	    	return df.format(new Date(d.getTime() + 2 * 60 * 60 * 1000));
	    }
	    
	}
	
	public static String formatTimeSta(Timestamp ts){
		String tsStr = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    try {   
            //方法一   
            tsStr = sdf.format(ts);   
            //System.out.println(tsStr);   
            //方法二   
//            tsStr = ts.toString();   
//            System.out.println(tsStr);   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
		
		return tsStr;
	}
	
	
	public static Date stringToDate(String str){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return date;
	}
	
	/*public static void main(String[] args) {
		
		Date date = DateUtils.stringToDate("Sat, 05-Aug-2017 00:41:44 GMT");
		System.out.println(date.toString());
		
	}*/
	
	

}
