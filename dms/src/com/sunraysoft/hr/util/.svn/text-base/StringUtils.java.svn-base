package com.sunraysoft.hr.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	
	public static String toUtf8Encode(String s) {
		String r = null;
		if(s != null) {
			try {
				r = URLEncoder.encode(URLEncoder.encode(s, "utf-8"));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	public static String toUtf8Encode2(String s) {
		String r = null;
		if(s != null) {
			try {
				r = URLEncoder.encode(s, "utf-8");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	public static String toUtf8Decode(String s) {
		String r = null;
		if(s != null) {
			try {
				r = URLDecoder.decode(s, "utf-8");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	


	public static boolean isNotNullOrBlank(String s) {
		return !isNullOrBlank(s);
	}

	public static boolean isNullOrBlank(String s) {
		return s == null || "".equals(s) || "0".equals(s) || "-1".equals(s);
	}

	public static boolean isNotNull(String s) {
		return !isNull(s);
	}

	public static boolean isNull(String s) {
		return s == null;
	}

	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}

	public static boolean isBlank(String s) {
		return "".equals(s);
	}
	
	public static List<Integer> stringToIntList(String s) {
		List<Integer> list = new ArrayList<Integer>();
		if(isNullOrBlank(s)){
			return list;
		}
		for (String id : s.split(",")) {
			list.add(Integer.valueOf(id));
		}
		return list;
	}
	
	public static List<Long> stringToLongList(String s) {
		List<Long> list = new ArrayList<Long>();
		if(isNullOrBlank(s)){
			return list;
		}
		for (String id : s.split(",")) {
			list.add(Long.valueOf(id));
		}
		return list;
	}

	public static boolean comparechange(String arg1,String arg2){
		arg1=arg1==null?"":arg1.trim();
		arg2=arg2==null?"":arg2.trim();
		if(arg1.endsWith(";")){
			arg1= arg1.substring(0, arg1.length()-1);
		}
		if(arg2.endsWith(";")){
			arg2= arg2.substring(0, arg2.length()-1);
		}
		if(arg1.length() == arg2.length()){
			if(arg1.equals(arg2)){
				return true;
			}else{
				String[] arg1s = arg1.split(";");
				for(int i = 0; i < arg1s.length; i ++){
					if(arg2.indexOf(arg1s[i])==-1){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
