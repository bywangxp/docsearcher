package ustc.sse.water.docsearcher.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ustc.sse.water.docsearcher.model.UserModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月15日 下午5:29:24 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月15日 下午5:29:24
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
public class LoginInterceptor implements HandlerInterceptor {
	public String[] allowUrls;

	public String[] getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	// 此方法是在执行handler之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		for (String string : allowUrls) {
			System.out.println(string);
		}
		String requestURI = request.getRequestURI();
		String url = requestURI.replaceAll(request.getContextPath(), "");
		System.out.println("登录的url" + url);
		// 此处读取配置文件，该配置文件中存放的是不需要经过登录即可访问的url
		if (url.indexOf("login.action") >= 1) {
			return true;
		}
		if (allowUrls != null && allowUrls.length >= 1) {
			for (String string : allowUrls) {
				if (string.contains(url)) {
					System.out.println("不需要验证的！！");
					return true;
				}
			}
		}
		System.out.println("需要验证权限的的网页");
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		if (user != null) {
			System.out.println(user.getUserName());
			return true; // 如果用户没有登录，则退出
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			System.out.println("未登录");
		}

		return false;

	}

	// 此方法是在执行handler过程中返回ModelAndView过程中执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	// 此方法是在执行handler之后执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
