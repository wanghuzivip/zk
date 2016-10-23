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
}