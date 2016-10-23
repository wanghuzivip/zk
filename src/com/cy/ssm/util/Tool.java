package com.cy.ssm.util;  
  
import java.io.IOException;
import java.util.Properties;  
  
/** 
 *  
 * 读取properties文件的工具类 
 * 
 */  
public class Tool {  
    private static Properties p = new Properties();  
  
    /** 
     * 读取properties配置文件信息 
     */  
    static{  
        try {  
            p.load(Tool.class.getClassLoader().getResourceAsStream("common.properties"));  
        } catch (IOException e) {  
            e.printStackTrace();   
        }  
    }  
    /** 
     * 根据key得到value的值 
     */  
    public static String getValue(String key)  
    {  
        return p.getProperty(key);  
    }  
}