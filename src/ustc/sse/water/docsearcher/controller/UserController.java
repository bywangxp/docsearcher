package ustc.sse.water.docsearcher.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.set.ListOrderedSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ustc.sse.water.docsearcher.dao.dao.PageDao;
import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.DocumentEbi;
import ustc.sse.water.docsearcher.service.ebi.UserEbi;
import ustc.sse.water.docsearcher.util.PPTUtils;
import ustc.sse.water.docsearcher.util.Pager;

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

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.GET }) // 后期改为post
	public Map<String, Object> login(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(userName + "" + password);
		UserModel userModel = new UserModel();
		userModel.setUserName(userName);
		userModel.setUserPassword(password);
		userModel = userEbi.findUser(userModel);
		HttpSession session = request.getSession();
		Map<String, Object> temp = new HashMap<String, Object>();
		ArrayList<String> list = new ArrayList<String>();
		// 登录成功，返回主页
		Map<String, Object> temp2 = new HashMap<String, Object>();
		if (userModel != null) {
			session.setAttribute("user", userModel);
			temp2.put("url", "/DocSearcher/index.html");
		} else {
			temp2.put("url", "/DocSearcher/login.html");
		}
		temp.put("data", temp2);

		return temp;
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
	//中栏列表信息反馈
	@ResponseBody
	@RequestMapping(value = "/home_middle", method = { RequestMethod.POST })
	public Map<String, Object> getHomeMiddle(HttpSession session) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//图片信息
		String []name=new String[4];
		name[0]="本月上传文档";
		name[1]="本月收藏文档";
		name[2]="本月文档被评论数";
		name[3]="平均分数";
		String []data=new String[4];
		//获取本月上传文档
		data[0]="123,32,32,32,2,3,2,3,2,3,2,23";
		//获取本月收藏文档
		data[1]="123,32,32,32,2,3,2,3,2,3,2,23";
		//本月文档被评论数
		data[2]="123,32,32,32,2,3,2,3,2,3,2,23";
		//平均分数
		data[3]="123,32,32,32,2,3,2,3,2,3,2,23";
		for(int i = 0 ; i < 4 ; ++i){
			HashMap<String, Object> hash = new HashMap<String,Object>();
			hash.put("name", name[i]);
			hash.put("type", "bar");
			String[] split = data[i].split(",");
			hash.put("data", split);
			list.add(hash);
		}
		map.put("series", list);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/home_right", method = { RequestMethod.POST })
	public Integer getHomeRight(HttpSession session,HttpServletRequest request) {
		//先获取用户
		UserModel user =(UserModel) session.getAttribute("user");
		String nickname = request.getParameter("nickname");
		String location = request.getParameter("location");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		//在修改用户信息
		if(nickname != null){//验证
			user.setUserNickName(nickname);
		}
		if(location != null){//验证
			user.setUserLocation(location);
		}
		if(phone != null){//验证
			user.setUserPhone(phone);
		}
		if(email != null){//验证
			user.setUserEmail(email);
		}
		Integer flag = userEbi.changeUserInfo(user);
		//成功返回1，失败返回0
		return flag;
	}
	//提交用户签名表单
	@ResponseBody
	@RequestMapping(value = "/home_sign", method = { RequestMethod.POST })
	public Integer getUserSign(HttpSession session,HttpServletRequest request) {
		//先获取用户
		UserModel user =(UserModel) session.getAttribute("user");		
		String description = request.getParameter("description");
		//在修改用户信息
		if(description != null){//验证
			user.setUserDescription(description);
		}
		Integer flag = userEbi.changeUserInfo(user);
		return flag;
	}
	@RequestMapping(value = "/home_img", method = RequestMethod.POST)
	public Integer getImg(@RequestParam MultipartFile image, HttpServletRequest request,HttpSession session) throws Exception {
		if (image.isEmpty()) {
			System.out.println("图片未上传");
			return 0;
		} else {
			String soureceName = image.getOriginalFilename();// 此文件名是带后缀的文件名（无路径）
		    //获取文件后缀
			String[] split = soureceName.split(".");
			//用户头像放在UserFile/用户名/photo/...
			String adjustName = "image"+split[split.length-1];	
			String absolutePath = session.getServletContext().getRealPath("/");
			PPTUtils.createDir(absolutePath + "UserFiles");
			InputStream inputStream = image.getInputStream();
			UserModel user =(UserModel) session.getAttribute("user");
			FileOutputStream out = new FileOutputStream(absolutePath + "UserFiles/"+user.getUserName()+"/photo/"+adjustName);
			// 文件写
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			out.close();
			System.out.println("文件输出成功");
			//修改数据库
			user.setUserPhone("UserFiles/"+user.getUserName()+"/photo/"+adjustName);
		    userEbi.changeUserInfo(user);
			return 1;
	}
	}
	
	
		// 用户贡献的文档信息
		@ResponseBody
		@RequestMapping(value = "/contri_data", method = { RequestMethod.POST })
		public Map<String, Object> getContributionDoc(HttpSession session,HttpServletRequest request) {
			int index = Integer.parseInt(request.getParameter("page"));//返回当前的页码值
			Pager pager = new Pager();//工具类，用于分页
			pager.setCurrentPage(index);
			//获取到用户的信息
			UserModel user =(UserModel) session.getAttribute("user");
			//获取指定用户所有的文档
			List<DocumentModel> list=documentEbi.getDocumentByUserId(user.getUserId(),pager);
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("totalpage", pager.getTotalPage());
			ArrayList<Map<String, Object>> doclist = new ArrayList<Map<String, Object>>();
            for (DocumentModel documentModel : list) {
            		HashMap <String, Object> docMap=new HashMap<String ,Object>();
            		docMap.put("logo", documentModel.getDocLogo());
            		docMap.put("docname", documentModel.getDocTitle());
            		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            		String date = simpleDateFormat.format(documentModel.getCreateTime());
            		docMap.put("uptime", date);
            		docMap.put("download", documentModel.getSumDownload());
            		docMap.put("credit", documentModel.getDocValue());
            		docMap.put("score", documentModel.getDocRating());
            		docMap.put("id", documentModel.getDocId());
            		doclist.add(docMap);
			}
             map.put("tablerow", doclist);
			return map;
		}
		//左侧用户信息展示
		@ResponseBody
		@RequestMapping(value = "/part", method = { RequestMethod.POST })
		public Map<String, Object> gerUserLeftInfo(HttpSession session,HttpServletRequest request) {
			//获取到用户的信息
			UserModel user =(UserModel) session.getAttribute("user");
			HashMap <String, Object> map=new HashMap<String ,Object>();
		    map.put("nickName", user.getUserNickName());
		    map.put("credit", user.getUserCredit());
		    //用户等级，待计算
		    map.put("level", "666");
		    map.put("docnum", user.getSumPublicDoc()+user.getSumPrivateDoc());
		    //用户评论数，待计算
		    map.put("ratenum", 0);
		    //用户收藏数目 ,待计算
		    map.put("collectnum", 0);
		    map.put("description", user.getUserDescription());
		    map.put("img", user.getUserPhoto());
			return map;
		
		}
				
}
