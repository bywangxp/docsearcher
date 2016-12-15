package ustc.sse.water.docsearcher.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInteceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception err)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
		System.out.println("postHandle");
		response.sendRedirect("/login.jsp");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String str = (String) request.getSession().getAttribute("userName");
		System.out.println("prehndler");
		System.out.println(str);
		if (str != null) {
			response.sendRedirect("/login.jsp");
		}
		return true;
	}

}
