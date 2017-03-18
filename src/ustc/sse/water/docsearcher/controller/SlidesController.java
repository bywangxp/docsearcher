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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.model.PageModel;
import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.DocumentEbi;
import ustc.sse.water.docsearcher.service.ebi.GlobalEbi;
import ustc.sse.water.docsearcher.service.ebi.PageEbi;
import ustc.sse.water.docsearcher.service.ebi.TagEbi;
import ustc.sse.water.docsearcher.service.ebi.UserEbi;
import ustc.sse.water.docsearcher.util.constant.PublicConstants;

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
	@Resource
	private TagEbi tagEbi;

	@Resource
	private PageEbi pageEbi;

	@Resource
	private DocumentEbi documentEbi;
	@Resource
	private GlobalEbi globalEbi;

	@ResponseBody
	@RequestMapping(value = "/download", method = { RequestMethod.POST })
	public Map<String, Object> downSlides(HttpServletRequest request) {
		String pageStr = request.getParameter("page_id");
		System.out.println("下载的page：" + pageStr);
		String[] page = pageStr.split(",");
		if (page.length == 0) {
			return null;
		}
		// 获取项目的绝对路径，用来存储用户的资源
		HttpSession session = request.getSession();
		String absolutePath = session.getServletContext().getRealPath("/");
		// 根据page_id返回下载链接
		ArrayList<Long> list = new ArrayList<Long>();
		for (int i = 0; i < page.length; ++i) {
			list.add(Long.parseLong(page[i]));
		}
		Map<String, Object> totalmap = new HashMap<String, Object>();
		String path = null;
		if (list.size() > 0) {
			path = pageEbi.downslides(absolutePath, list);
		}
		String downloadPath = null;
		if (path != null) {
			downloadPath = "UserFiles/download/" + path + ".pdf";
		}
		totalmap.put("downloadPath", downloadPath);
		return totalmap;

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
		Boolean flag = documentEbi.uploadFiles(myfiles, request);
		HttpSession session = request.getSession();
		long end = System.currentTimeMillis();
		System.out.println("整个解析流程用时:" + (end - start) / 1000 + "s");
		if (flag) {
			// 上传成功，获取文件信息，存储进

			session.setAttribute("uploadInfo", "上传成功");

			return "/jsps/success.jsp";
		} else {
			session.setAttribute("uploaderror", "上传失败");
			return "redirect:/jsps/upload.jsp";
		}

	}

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
		// TODO 因为之前测试阶段，删除了一些乱码的document导致，通过一些page 找document报空指针异常，需要对数据进行一次处理
		Long pageId = Long.parseLong(request.getParameter("pageid"));
		// pageid是单页ppt的id好，在page表中全局唯一，可以获取到对应的文档id
		System.out.println("传递的参数pageid" + pageId);
		PageModel pageModel = pageEbi.getPageByPageId(pageId);
		Long docId = pageModel.getDocId();
		System.out.println("查询得到docuemntId：" + docId);
		// 获取到对应的文档
		DocumentModel documentModel = documentEbi.getDocumentByDocId(docId);
		// 获取该文档下面的全部页面数量
		List<PageModel> page = pageEbi.getPageListByDocId(docId);
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
		temp.put("pageNow", pageId);
		temp.put("pageAll", documentModel.getSumPage());
		temp.put("author", userModel.getUserName());
		temp.put("logo", documentModel.getDocLogo());
		temp.put("date", date);
		temp.put("inf", pageModel.getPageDescription());
		temp.put("downloadUrl", downloadPath);
		temp.put("downloadNum", documentModel.getSumDownload());
		temp.put("addFavNum", documentModel.getSumCollection()); // System.out.println(temp);
		// 判断是否收藏
		int flag = pageEbi.whetherAddFav(request, pageId);
		String fav = PublicConstants.IF_ADD_FAV;
		if (flag == 0) {
			fav = PublicConstants.IF_NO_ADD_FAV;
		}
		temp.put("ifaddFav", fav);
		totalmap.put("slide", temp);
		return totalmap;

	}

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
		globalEbi.saveSearchRecord(request, keyword);
		// 处理异常，此处可能收到未空的数据
		long sort = Long.parseLong(request.getParameter("sort"));
		int mine = Integer.parseInt(request.getParameter("mine"));// 是否只显示我的文库数据：0否，1是
		long sortId = Long.parseLong(request.getParameter("sort_id"));
		long tagId = Long.parseLong(request.getParameter("kid"));
		System.out.println("发起一次get_all_slides:keyword" + keyword + " ;sort" + sort + ";mine" + mine + " ;sortId"
				+ sortId + " ;tagId" + tagId);
		// 组装json
		Map<String, Object> totalmap = new HashMap<String, Object>();
		totalmap.put("errcode", Integer.toString(0));
		totalmap.put("errmsg", "");
		List<PageModel> pages = null;
		if (keyword == "") {
			// 默认搜索显示全部信息
			ArrayList<DocumentModel> allDoc = documentEbi.getAllDocumentModel();
			System.out.println("总共有：" + allDoc.size());
			pages = new ArrayList<PageModel>();
			List<PageModel> pageListByDocId = null;
			for (DocumentModel documentModel : allDoc) {
				pageListByDocId = pageEbi.getPageListByDocId(documentModel.getDocId());
				if (pageListByDocId.size() > 0) {
					pages.add(pageListByDocId.get(0));
				}
			}
		} else {
			pages = pageEbi.getPageByKeyWord(keyword, request);
		}
		// 根据关键字检索
		// mine,只显示用户自身的ppt
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		Long userId = user.getUserId();
		System.out.println("userID:" + userId);
		for (PageModel pageModel : pages) {
			System.out.println("未过滤之前查询到的结果pageid：" + pageModel.getPageId());
		}
		List<Map<String, Object>> pagesList = new ArrayList<Map<String, Object>>();
		for (PageModel pageModel : pages) {
			// 根据pageid找到所属的文档
			DocumentModel documentModel = documentEbi.getDocumentByDocId(pageModel.getDocId());
			if (mine == 1 && userId != documentModel.getUserId()) {// 当mine=1.则过滤不属于自己的文档
				continue;
			}
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
				String info = null;
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("id", pageModel.getPageNo());
				temp.put("pic", "UserFiles/" + documentModel.getDocSaveKey() + "/images/" + pageModel.getPagePreview());
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
				info = docTitle.replace(keyword, "<span>" + keyword + "</span>");
				System.out.println("info" + info);
				temp.put("inf", info);
				temp.put("downloadUrl", downloadPath);
				temp.put("downloadNum", documentModel.getSumDownload());
				temp.put("addFavNum", documentModel.getSumCollection());
				// 为了记录收藏，取消收藏设定的
				temp.put("doc_id", documentModel.getDocId());
				temp.put("page_id", pageModel.getPageId());
				// 一张表中两个显示收藏
				// 查询针对当前用户，页面是否被收藏
				int flag = pageEbi.whetherAddFav(request, pageModel.getPageId());
				String fav = PublicConstants.IF_ADD_FAV;
				if (flag == 0) {
					fav = PublicConstants.IF_NO_ADD_FAV;
				}
				temp.put("ifaddFav", fav);
				temp.put("ifChoose", "images/result/choose1.png");
				temp.put("choose", false);
				pagesList.add(temp);
			}
			// 搜索结束
			totalmap.put("slide", pagesList);
		}
		return totalmap;
	}

	@ResponseBody
	@RequestMapping(value = "/compose_pdf", method = { RequestMethod.POST })
	public Map<String, Object> composePDF(HttpServletRequest request) {
		// 获取合成的pdf文件名称filename
		String filename = "myfile";// 需要对文件名称进行检查，此名称作为文件名
		// 需要打印的页面pid
		// 考虑数组为空，的情况，前台需要判断
		long[] pagesId = { 898, 876, 803, 805, 753, 758 };
		String path = pageEbi.composePDF(request, pagesId, filename);
		globalEbi.saveDownLoadRecord(request, pagesId);
		System.out.println("success");
		return null;
	}

	// 添加与取消收藏

	@RequestMapping(value = "/addfav", method = { RequestMethod.POST })
	public void SaveCollection(HttpServletRequest request) {
		Long pageId = Long.parseLong(request.getParameter("page_id"));
		Long docId = Long.parseLong(request.getParameter("doc_id"));
		pageEbi.saveCollection(docId, pageId, request);

	}

}
