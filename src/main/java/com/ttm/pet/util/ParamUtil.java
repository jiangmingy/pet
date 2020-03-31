package com.ttm.pet.util;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class ParamUtil {

	
	public static Double toDouble(String parameter){
		if(parameter == null || ("").equals(parameter)){
			return null;
		}
		return Double.parseDouble(parameter);
	}
	
	public static Integer toInteger(String parameter){
		if(parameter == null || ("").equals(parameter)){
			return null;
		}
		return  Integer.parseInt(parameter);
	}
	
	/**
	 * 字符串去空
	 * @param parameter
	 * @return
	 */
	public String handleStringParameter(String parameter){
		String handleString = "";
		if(parameter!=null&&!("").equals(parameter)){
			handleString = parameter.trim();
		}
		return handleString;
	}
	
	/**
	 * 字符串去空
	 * @param parameter
	 * @return
	 */
	public String handleObjectToStringParameter(Object parameter){
		String handleString = "";
		if(parameter!=null){
			if(!("").equals(parameter)){
				handleString = parameter.toString().trim();
			}
		}
		return handleString;
	}
	
	
	/**
	 * String 转  int
	 * @param parameter
	 * @return
	 */
	public int handleIntegerParameter(String parameter){
		int handleInteger = 0;
		if(parameter!=null&&!("").equals(parameter)){
			handleInteger = Integer.parseInt(parameter.trim());
		}
		return handleInteger;
	}
	
	/**
	 * 时间转string
	 * @param date
	 * @return
	 */
	public String handleDateToString(Date date){
		String timeStr = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeStr = formatter.format(date);
		return timeStr;
	}
	
	/**
	 * 字体转码
	 * @param parameter
	 * @return
	 */
	public String StringEscapeUtils(String parameter){
		String timeStr = "";
		if(parameter!=null&&!("").equals(parameter)){
			parameter = parameter.trim();
			timeStr = StringEscapeUtils.unescapeJava(parameter);
		}
		return timeStr;
	}
	
	/**
	 * 获取今天凌晨00：00：00的时间戳
	 * @param
	 * @return
	 */
	public long getNowTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 000);
		return cal.getTimeInMillis();
	} 
	
	/**
	 * 转时间戳
	 * @param time
	 * @return
	 */
	public long getTimestamp(String time){
		long timestamp = 0L;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        if(time!=null&&!("").equals(time)){
        	try {
    			timestamp = sf.parse(time).getTime();
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
        }
        return timestamp;
	}
	
	public static String toPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(password.getBytes("utf-8")));
        newstr=base64en.encode(md5.digest((newstr+"pet").getBytes("utf-8")));
        return newstr;
	}
	
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		System.out.println(toPassword("pet"));
		
		
	}
}
