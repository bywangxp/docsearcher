package ustc.sse.water.docsearcher.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.model.PageModel;
import ustc.sse.water.docsearcher.model.TagModel;
import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.UserEbi;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月15日 下午5:18:07 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月15日 下午5:18:07
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Controller
@RequestMapping("/slides")
public class SlidesController {

	@Resource
	private UserEbi userEbi;

	// 以下接口为ppt展示页
	// 获取指定PPT的所有页面的缩略图
	/**
	 * 
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年12月15日 下午5:18:37 修改人 修改说明 <br>
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 * @throws Exception
	 *             异常说明
	 */
	@ResponseBody
	@RequestMapping(value = "/get_all_slides_img", method = { RequestMethod.POST })
	public Map<String, Object> getAllSlidesImg(HttpSession session, HttpServletRequest request) throws Exception {
		Integer pageid = Integer.parseInt(request.getParameter("pageid"));
		// pageid是单页ppt的id好，在page表中全局唯一，可以获取到对应的文档id
		System.out.println("传递的参数pageid" + pageid);
		PageModel pageModel = userEbi.getPageByPageId(pageid);
		Long docId = pageModel.getDocId();
		System.out.println("查询得到docuemntId：" + docId);
		System.out.println("传递的参数pageid" + pageid);
		// 获取到对应的文档
		DocumentModel documentModel = userEbi.getDocumentsByDocId(docId);

		// 获取该文档下面的全部页面数量
		List<PageModel> page = userEbi.getPage(docId);
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();

		List<Map<String, Object>> pagesList = new ArrayList<Map<String, Object>>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");
		Integer sumPage = documentModel.getSumPage();

		// 遍历整个页面
		for (int i = 0; i < page.size(); ++i) {
			PageModel pageTempModel = page.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			// 做一些修改的页号
			String num = "";
			Integer pageNo = pageTempModel.getPageNo();
			// 页面num返回若是1-9则为01-09 添加前导0
			if (pageNo < 10) {
				num += 0;
			}
			num += pageNo;

			map.put("num", num);

			String pagePreview = pageTempModel.getPagePreview();
			String pageSaveKey = documentModel.getDocSaveKey();
			// 拼出页面的路径
			String pic = "UserFiles/" + pageSaveKey + "/images/" + pagePreview;
			map.put("pic", pic);
			pagesList.add(map);
		}
		totalmap.put("pages", pagesList);

		// 拼出下载地址：PPT文档下载的地址1477990439175_PPT_Chapter08.ppt

		String fileType = documentModel.getFileType();
		// 获取后缀
		String downloadPath = "UserFiles/" + documentModel.getDocSaveKey() + "/" + documentModel.getDocSaveKey() + "."
				+ fileType;
		// 将日期转换 Date createTime = documentModel.getCreateTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String date = simpleDateFormat.format(documentModel.getCreateTime());
		// 获取用户信息
		Long userId = documentModel.getUserId();
		UserModel userModel = userEbi.getUserById(userId);
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("pic", documentModel.getDocPreview());
		temp.put("name", documentModel.getDocTitle());
		temp.put("coin", documentModel.getDocValue());
		temp.put("grade", documentModel.getDocRating());
		// pageNow 先以pageid代替
		temp.put("pageNow", pageid);
		temp.put("pageAll", documentModel.getSumPage());
		temp.put("author", userModel.getUserName());
		temp.put("logo", documentModel.getDocLogo());
		temp.put("date", date);
		temp.put("inf", pageModel.getPageDescription());
		temp.put("downloadUrl", downloadPath);
		temp.put("downloadNum", documentModel.getSumDownload());
		temp.put("addFavNum", documentModel.getSumCollection()); // System.out.println(temp);
		temp.put("ifaddFav", "images/result/star1.png"); // System.out.println(temp);
		totalmap.put("slide", temp);
		return totalmap;

	}

	/**
	 * 
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年11月5日 下午8:37:48 修改人 修改说明 <br>
	 * 
	 * @param 参数名
	 *            参数说明
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */
	// 以下是搜素结果页

	/**
	 * 
	 * 方法说明 <br>
	 * <p>
	 * 修改历史: 2016年11月6日 下午7:30:13 修改人 修改说明 <br>
	 * 
	 * @param search:检索内容参数说明
	 *            tagId：分类id sortId:排序方式id，1文件名 2贡献者 3下载次数 4收藏次数 5 评分 6上传时间
	 * 
	 *            sort:排序方式，0降序，1升序
	 * 
	 *            mine: 是否只显示我的文库数据，0否，1是
	 * 
	 * @return 返回结果说明
	 * @throws Exception
	 *             异常说明
	 */
	// 根据分类等信息获取PPT信息
	// 所属页面

	@ResponseBody
	@RequestMapping(value = "/get_all_slides", method = { RequestMethod.POST })
	public Map<String, Object> searchSlides(HttpServletRequest request) {
		String keyword = request.getParameter("search");
		if (keyword == null) {
			keyword = "";
		}
		System.out.println("检索的关键字是：" + keyword);
		long sort = Long.parseLong(request.getParameter("sort"));
		long mine = Long.parseLong(request.getParameter("mine"));
		long sortId = Long.parseLong(request.getParameter("sort_id"));
		long tagId = Long.parseLong(request.getParameter("kid"));
		System.out.println("检索条件的tagid是：" + tagId);
		// 根据关键字检索
		// 根据关键字搜索到相关的文档list集合
		List<DocumentModel> list = userEbi.searchSlides(keyword);
		for (DocumentModel documentModel : list) {
			System.out.println("根据关键字检索到的：" + documentModel.getDocTitle());
		}
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();

		List<Map<String, Object>> pagesList = new ArrayList<Map<String, Object>>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");

		for (DocumentModel documentModel : list) {
			/*
			 * System.out.println("搜索的tagid" + documentModel.getTagId());
			 * System.out.println("当前tagid" + tagId);
			 * System.out.println("当前文档的名称" + documentModel.getDocTitle());
			 * System.out.println("当前文档的id" + documentModel.getDocId());
			 */
			// 若有分类编号，则不在分类中的文档不显示
			// 遍历检索结果，resultTagId为搜索结果的tagid
			Long resultTagId = documentModel.getTagId();
			// System.out.println("搜索结果的tagid与搜索条件的tagid相等吗？ " +
			// documentModel.getTagId().equals(tagId));
			// 全局分类tagid为1，此处不需要和resultTagId相等，均展示
			if (tagId == 1 || resultTagId == tagId) {
				String fileType = documentModel.getFileType();
				// 获取后缀
				String downloadPath = "UserFiles/" + documentModel.getDocSaveKey() + "/pdf/"
						+ documentModel.getDocSaveKey() + ".pdf";
				// 将日期转换 Date createTime = documentModel.getCreateTime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
				String date = simpleDateFormat.format(documentModel.getCreateTime());
				Long docId = documentModel.getDocId();
				// 获取文档下面的全部页面数量
				List<PageModel> page = userEbi.getPage(docId);
				System.out.println("文档页数" + page.size());
				String info = null;
				for (int i = 0; i < 3; ++i) {
					System.out.println("查找的页面数：" + page.get(i).getPageNo());
					System.out.println("保存页面信息");
					PageModel pageModel = page.get(i);
					Map<String, Object> temp = new HashMap<String, Object>();
					temp.put("id", pageModel.getPageNo());
					temp.put("pic", "UserFiles/" + documentModel.getDocSaveKey() + "/images/"
							+ documentModel.getDocSaveKey() + "_" + (i + 1) + ".png");
					temp.put("name", documentModel.getDocTitle());
					temp.put("coin", documentModel.getDocValue());
					temp.put("grade", documentModel.getDocRating());
					// pageNow 先以pageid代替
					temp.put("pageNow", pageModel.getPageNo());
					temp.put("pageAll", documentModel.getSumPage());
					// 获取用户名
					UserModel userModel = userEbi.getUserById(documentModel.getUserId());
					temp.put("author", userModel.getUserName());
					documentModel.getDocLogo();
					String docLogo = documentModel.getDocLogo();
					// 后期修改，若用户上传则是用户上传的头像
					temp.put("logo", docLogo);
					temp.put("date", date);
					String docTitle = documentModel.getDocTitle();
					System.out.println(docTitle);
					if (i == 0) {
						info = docTitle.replace(keyword, "<span>" + keyword + "</span>");
						System.out.println("info" + info);

					}
					temp.put("inf", info);
					temp.put("downloadUrl", downloadPath);
					temp.put("downloadNum", documentModel.getSumDownload());
					temp.put("addFavNum", documentModel.getSumCollection());
					// 一张表中两个显示收藏
					String fav = "images/result/star1.png";
					if (i == 0) {
						fav = "images/result/star2.png";
					}
					temp.put("ifaddFav", fav);
					temp.put("ifChoose", "images/result/choose1.png");
					temp.put("choose", false);
					pagesList.add(temp);
				}

			}
		}
		// 搜索结束
		totalmap.put("slide", pagesList);

		return totalmap;

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

	// 获取用户分类标签
	@ResponseBody
	@RequestMapping(value = "/get_all_kinds", method = { RequestMethod.POST })
	public Map<String, Object> GetAllTags(HttpSession session, Integer kid) {
		List<TagModel> list = userEbi.getAllTags();
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");
		int size = list.size();
		int index = 0;
		// 分类
		List<Map<String, Object>> kingList = new ArrayList<Map<String, Object>>();

		// 查询数据库，此tag下有多少数量的文档
		Long documentbyTag[] = new Long[list.size()];
		documentbyTag[0] = (long) 0;
		// TODO 查询所有对应分类的文件数，tagid=1不查询，后期对应没有分类的文件可能会放置放在。
		for (int i = 1; i < list.size(); ++i) {

			Long tagId = list.get(i).getTagId();
			documentbyTag[i] = userEbi.getDocumentsByTags(tagId);
			System.out.println(i + ":" + documentbyTag[i]);
		}
		// tagid=1 就i=0为其他分类的和
		for (int i = 1; i < list.size(); ++i) {
			documentbyTag[0] += documentbyTag[i];

		}

		// 获取到按照id号排序的tag时
		for (int i = 0; i < list.size(); ++i) {
			Map<String, Object> map = new HashMap<String, Object>();
			TagModel tagModel = list.get(i);
			Long tagId = tagModel.getTagId();
			System.out.println(tagId + "+" + tagModel.getTagName());
			map.put("id", Long.toString(tagId));
			map.put("num", Long.toString(documentbyTag[i]));
			map.put("name", tagModel.getTagName());
			Boolean flag = false;
			if (tagId == 1) {
				flag = true;
			}
			map.put("active", flag);
			kingList.add(map);
		}
		totalmap.put("kind", kingList);
		return totalmap;
	}
}
