package com.bookshop.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop.model.User;
import org.springframework.stereotype.Component;

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
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");

		/*
		打印请求内容
		 */


		String currentPath = ((HttpServletRequest)request).getRequestURI();
		System.out.println("current url:"+currentPath);
		System.out.println("HTTP_METHOD : " + ((HttpServletRequest)request).getMethod());
		System.out.println("HTTP_HEAD Type : " + ((HttpServletRequest)request).getHeader("Content-Type"));
		System.out.println("IP : " + request.getRemoteAddr());

		// 排除主页
		if (currentPath.endsWith("/book/SelectByBookId")||currentPath.endsWith("/book/AllBook")) {
			chain.doFilter(request, response);
			return;
        }

		// 排除登录请求
		if (currentPath.endsWith("/") || currentPath.endsWith("/userlogin")|| currentPath.endsWith("/adminlogin") ) {
			chain.doFilter(request, response);
			return;
        }
		
		HttpSession session=((HttpServletRequest)request).getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			System.out.println("用户为空"+user);
			response.getWriter().print("false");
		}
		else{
			System.out.println("有用户"+user);
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
