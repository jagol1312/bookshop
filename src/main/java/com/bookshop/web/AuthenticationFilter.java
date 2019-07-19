package com.bookshop.web;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.model.User;
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
		if (currentPath.endsWith("/book/SelectByBookId")||currentPath.endsWith("/book/AllBook")||currentPath.endsWith("/")){
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
		HttpSession session=((HttpServletRequest)request).getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			System.out.println("user is empty："+user);
			((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin","*");//接受任意域名的请求，允许所有域名跨域
			((HttpServletResponse)response).addHeader("Access-Control-Expose-Headers", "REDIRECT,CONTEXTPATH");//服务器 headers 白名单，可以让客户端获得到响应头
			((HttpServletResponse)response).addHeader("REDIRECT", "REDIRECT");//告诉ajax这是重定向
			((HttpServletResponse)response).addHeader("CONTEXTPATH","login.html");//重定向地址
			System.out.println("go into redirect！");
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
