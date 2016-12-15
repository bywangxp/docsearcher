package ustc.sse.water.docsearcher.test;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年10月29日 下午9:47:11 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月29日 下午9:47:11
 * <p>
 * Company: 中科大软件学院
 * <p>
 * @author 苏思畅 susc@mail.ustc.edu.cn
 * @version 版本号
 */
@Controller
public class TestController {
	
	@Resource
	private TestDAO testDAO;
	
	/**
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年10月29日 下午9:47:21 修改人
	 * 修改说明 <br>
	 * 
	 * @param 参数名 参数说明
	 * @return 返回结果说明
	 * @throws Exception 异常说明
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String printTest(HttpServletRequest request, ModelMap modelMap) {
		//int id = Integer.parseInt(request.getParameter("testid"));
		int id = 1;
		Test result = testDAO.findOne(id);
		modelMap.put("testid", result.getId());
		modelMap.put("testname", result.getName());
		return "index";
	}

}
