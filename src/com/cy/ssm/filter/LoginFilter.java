package com.cy.ssm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse res = (HttpServletResponse) arg1;
		//��SESSION��ȡ����
		Object obj = req.getSession().getAttribute("u");
		String servletPath = req.getServletPath();
		if(obj != null){
			arg2.doFilter(arg0, arg1);//�������Լ����������������ҳ��JS,JSP,HTML
		}else{
			String path = req.getContextPath();
			String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
			
			if(servletPath.contains(".")){
				int index = servletPath.lastIndexOf(".");
				String suffix = servletPath.substring(index);
				if(".js.html.css.jpg.png.jsp".contains(suffix)){
					arg2.doFilter(arg0, arg1);
				}else{//�ض���
					res.sendRedirect(basePath);
				}
			}else{//�����Ǵ��������Լ���������
				if(servletPath.contains("login")||servletPath.contains("regist")){
					arg2.doFilter(arg0, arg1);
				}else{//�ض���
					res.sendRedirect(basePath);
				}
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
