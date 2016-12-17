package ustc.sse.water.docsearcher.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
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

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadPPT(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws Exception {
		// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
		// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
		// 并且上传多个文件时，前台表单中的所有<input
		// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
		long start = System.currentTimeMillis();
		System.out.println("正在执行上传操作");
		// 设置转换成功与否的标志
		// 获取项目的绝对路径，用来存储用户的资源
		HttpSession session = request.getSession();
		String absolutePath = session.getServletContext().getRealPath("/");
		System.out.println(absolutePath);

		// 通过session获取当前用户的用户名信息，用于创建文件夹
		UserModel userModel = (UserModel) session.getAttribute("user");
		Boolean flag = documentEbi.uploadFiles(myfiles, absolutePath, userModel);

		long end = System.currentTimeMillis();
		System.out.println("整个解析流程用时:" + (end - start) / 1000 + "s");
		if (flag) {
			// 上传成功，获取文件信息，存储进

			session.setAttribute("uploadInfo", "上传成功");
			return "redirect:/jsps/success.jsp";
		} else {
			session.setAttribute("uploaderror", "上传失败");
			return "redirect:/jsps/upload.jsp";
		}

	}

}
