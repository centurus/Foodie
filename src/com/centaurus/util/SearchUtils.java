package com.centaurus.util;

public class SearchUtils {
	 //转换为unicode  
    public static String encodeUnicode(final String gbString) {     
            char[] utfBytes = gbString.toCharArray();     
            String unicodeBytes = "";     
            for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {     
                String hexB = Integer.toHexString(utfBytes[byteIndex]);     
                if (hexB.length() <= 2) {     
                    hexB = "00" + hexB;     
                }     
                unicodeBytes = unicodeBytes + "\\u" + hexB;     
            }     
            return unicodeBytes;  
        }


    //<span class='ecook'>豆腐</span>
    public static String changeStringColor(String str){
    	String subs1="<span class='ecook'>";
    	String subs2="</span>";
    	str=str.replaceAll(subs1, "<font color='#ff7f00'>");
    	str=str.replaceAll(subs2, "</font>");
    	return str;
    }
    public static String changeString(String str){
    	String subs1="<font color='#ff7f00'>";
    	String subs2="</font>";
    	str=str.replaceAll(subs1, "");
    	str=str.replaceAll(subs2, "");
    	return str;
    }
  
}
