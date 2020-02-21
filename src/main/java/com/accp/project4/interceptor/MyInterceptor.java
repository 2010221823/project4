package com.accp.project4.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

public class MyInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		Object object = request.getSession().getAttribute("user");
		if (object == null) {
			if (request.getRequestURI().startsWith(request.getContextPath() + "/api")) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				Map<String, Object> message = new HashMap<String, Object>();
				message.put("code", "300");
				message.put("msg", "Do Login");
				out.write(JSON.toJSONString(message));
				out.flush();
			} else if (request.getRequestURI().startsWith(request.getContextPath() + "/")) {
				response.sendRedirect(request.getContextPath() + "/ui404");
			}
			return false;
		}
		return true;
	}

}
