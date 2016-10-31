package com.cy.ssm.util;  
  
import java.io.IOException;
import java.util.Properties;  
  
/** 
 *  
 * ��ȡproperties�ļ��Ĺ����� 
 * 
 */  
public class Tool {  
    private static Properties p = new Properties();  
    private static String downLoadUrl = null;
  
    /** 
     * ��ȡproperties�����ļ���Ϣ 
     */  
    static{  
        try {  
            p.load(Tool.class.getClassLoader().getResourceAsStream("common.properties"));  
        } catch (IOException e) {  
            e.printStackTrace();   
        }  
    }  
    /** 
     * ����key�õ�value��ֵ 
     */  
    public static String getValue(String key)  
    {  
        return p.getProperty(key);  
    }  
    
    public static String getDownLoadUrl( ){  
    	if(downLoadUrl == null){
    		downLoadUrl =  p.getProperty("dowload.url"); 
        }
    	 return downLoadUrl;
    } 
    
    
}