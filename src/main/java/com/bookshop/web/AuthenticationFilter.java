package com.bookshop.web;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.model.User;
import com.bookshop.util.JSONUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
 * 身份验证过滤器，只有登录用户才可以访问。
 * 注意，这里拦截所有请求,除了登录和主页
 */

@Component
@WebFilter(filterName="authFilter", urlPatterns="/*")
public class AuthenticationFilter implements Filter  {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,REDIRECT,CONTEXTPATH");
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin","http://39.106.86.107:8080");//接受域名的请求，
		((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin","null");
		((HttpServletResponse)response).setHeader("Access-Control-Expose-Headers", "REDIRECT,CONTEXTPATH");//服务器 headers 白名单，可以让客户端获得到响应头
		/*
		打印请求内容
		 */
		String currentPath = ((HttpServletRequest)request).getRequestURI();
		System.out.println("-------------------request server-----------------");
		System.out.println("current url:"+currentPath);
		System.out.println("HTTP_METHOD : " + ((HttpServletRequest)request).getMethod());
		System.out.println("HTTP_HEAD Type : " + ((HttpServletRequest)request).getHeader("Content-Type"));
		System.out.println("IP : " + request.getRemoteAddr());
		System.out.println("-------------------request   end-----------------");
		// 排除主页和书籍详情页
		if (currentPath.endsWith("/book/SelectByBookId")||currentPath.endsWith("/book/AllBook")||currentPath.endsWith("/upload")){
			chain.doFilter(request, response);
			return;
        }
		//排除查询书籍
        if (currentPath.endsWith("/book/SelectByName")|| currentPath.endsWith("/book/GetNewBook") ) {
            chain.doFilter(request, response);
            return;
        }
		// 排除登录请求
		if (currentPath.endsWith("/userlogin")|| currentPath.endsWith("/adminlogin") ) {
			chain.doFilter(request, response);
			return;
        }
		if(currentPath.endsWith("/notify")|| currentPath.endsWith("/returnUrl")){
			chain.doFilter(request, response);
			return;
		}
		if(currentPath.endsWith("/Kaptcha")){
			chain.doFilter(request, response);
			return;
		}
		if(currentPath.endsWith("/user/list")||currentPath.endsWith("/user/deleteuser")||currentPath.endsWith("/book/AddBook")||currentPath.endsWith("/book/DeleteBook")||currentPath.endsWith("/book/EditBook")||currentPath.endsWith("/")){
			HttpSession session=((HttpServletRequest)request).getSession();
			User admin = (User)session.getAttribute("admin");
			if(admin==null){
				System.out.println("admin is empty："+admin);
				((HttpServletResponse)response).setHeader("REDIRECT", "REDIRECT");//告诉ajax这是重定向
				((HttpServletResponse)response).addHeader("contextpath","adminlogin.html");//重定向地址
			}
			else{
				System.out.println("admin is existence："+admin);
				chain.doFilter(request, response);
			}
		}
		HttpSession session=((HttpServletRequest)request).getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			System.out.println("user is empty："+user);

			((HttpServletResponse)response).setHeader("REDIRECT", "REDIRECT");//告诉ajax这是重定向
			((HttpServletResponse)response).addHeader("CONTEXTPATH","login.html");//重定向地址
		}
		else{
			System.out.println("user is existence："+user);
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
