package ustc.sse.water.docsearcher.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.DocumentEbi;
import ustc.sse.water.docsearcher.service.ebi.UserEbi;

/**
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年10月30日 下午7:47:42 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月30日 下午7:47:42
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserEbi userEbi;

	@Resource
	private DocumentEbi documentEbi;

	@RequestMapping(value = "/login", method = { RequestMethod.GET }) // 后期改为post
	public String login(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(userName + "" + password);
		UserModel userModel = new UserModel();
		userModel.setUserName(userName);
		userModel.setUserPassword(password);
		userModel = userEbi.findUser(userModel);
		HttpSession session = request.getSession();
		// 登录成功，返回主页
		if (userModel != null) {
			session.setAttribute("user", userModel);
			return "redirect:/jsps/success.jsp";// 登录成功去的页面，待修改
		} else {
			// 失败，返回错误信息，并返回到登录页面
			session.setAttribute("errorinfo", "登录出错");
			return "redirect:/login.jsp";
		}

	}

	/**
	 * 
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年10月30日 下午10:36:21 修改人 修改说明 <br>
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.POST })
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/WEB-INF/hello.jsp";// 退出登录时，去的页面，待修改
	}

	// 获取用户详细信息
	@ResponseBody
	@RequestMapping(value = "/get_user_detail", method = { RequestMethod.POST })

	public Map<String, Object> getUserDetail(HttpSession session) {
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");
		UserModel userModel = (UserModel) session.getAttribute("user");
		// 获取用户详细信息
		Long userId = userModel.getUserId();
		map.put("id", Long.toString(userId));
		String userPhoto = userModel.getUserPhoto();
		System.out.println(userPhoto + ":" + userPhoto);
		map.put("logo", userPhoto);
		// 在用户注册时 创建文件夹
		Integer userCredit = userModel.getUserCredit();
		map.put("coin", Integer.toString(userCredit));
		String userName = userModel.getUserName();
		map.put("name", userName);
		String userDescription = userModel.getUserDescription();
		map.put("info", userDescription);
		System.out.println("info:" + userDescription);
		Integer sumPublicDoc = userModel.getSumPublicDoc();
		Integer sumPrivateDoc = userModel.getSumPrivateDoc();// 用户私有文档
		map.put("myDoc", Integer.toString(sumPrivateDoc));
		Integer sumDoc = sumPublicDoc + sumPrivateDoc;// 用户总文档
		map.put("allDoc", Integer.toString(sumDoc));
		// TODO 用户收藏文档数
		Integer sumCollection = 16;
		map.put("fav", Integer.toString(16));
		// TODO 用户下载文档数
		Integer sumDownload = 0;
		map.put("download", Integer.toString(0));
		totalmap.put("user", map);
		return totalmap;
	}

	// 获取用户基本信息
	@ResponseBody
	@RequestMapping(value = "/get_user", method = { RequestMethod.POST })
	public Map<String, Object> getUser(HttpSession session) {
		Map<String, Object> totalmap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");
		UserModel userModel = (UserModel) session.getAttribute("user");
		String userName = userModel.getUserName();
		// TODO 用户等级后期添加
		String userLevel = "LV10";
		map.put("name", userName);
		map.put("level", userLevel);
		totalmap.put("person", map);
		return totalmap;

	}

}
