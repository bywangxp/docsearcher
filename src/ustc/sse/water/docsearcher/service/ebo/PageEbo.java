package ustc.sse.water.docsearcher.service.ebo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.PageDao;
import ustc.sse.water.docsearcher.model.PageModel;
import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.PageEbi;
import ustc.sse.water.docsearcher.service.solve.ConvertToPdf;
import ustc.sse.water.docsearcher.service.solve.search.ExcuteQuery;
import ustc.sse.water.docsearcher.util.PPTUtils;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月18日 上午1:09:13 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月18日 上午1:09:13
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Service("pageEbi")
public class PageEbo implements PageEbi {

	@Resource
	private PageDao pageDao;

	/*
	 * @Override public List<PageModel> getPageListByUserId(Long userId) {
	 * 
	 * List<PageModel> list = pageDao.getPageListByUserId(userId); return list;
	 * }
	 */
	@Override
	public List<PageModel> getPageListByDocId(Long docId) {

		List<PageModel> list = pageDao.getPageListByDocId(docId);
		return list;
	}

	@Override
	public PageModel getPageByPageId(long pageid) {
		PageModel pageMode = pageDao.getPageByPageId(pageid);
		return pageMode;
	}

	@Override
	public String composePDF(HttpServletRequest request, long[] pagesId, String filename) {
		// 获取项目的绝对路径，用来存储用户的资源
		HttpSession session = request.getSession();
		String absolutePath = session.getServletContext().getRealPath("/");
		UserModel userModel = (UserModel) session.getAttribute("user");
		String userName = userModel.getUserName();
		if (userModel == null || filename.equals("")) {
			return null;
		}
		// 用户必须登录，才有权限下载文件
		// Todo 部分文件的创建应该放在用户注册阶段，如用户的私有文件夹
		PPTUtils.createDir(absolutePath + "UserFiles\\PrivateRepository\\" + userName + "\\composePDF");
		System.out.println(absolutePath);
		ConvertToPdf convertToPdf = new ConvertToPdf();
		String path = absolutePath + "UserFiles\\PrivateRepository\\" + userName + "\\composePDF\\" + filename + ".pdf";
		int size = pagesId.length;
		String[] pageSaveKey = new String[size];
		int[] num = new int[size];
		for (int i = 1; i <= size; i++) {

			PageModel pageModel = pageDao.getPageByPageId(pagesId[i - 1]);
			pageSaveKey[i - 1] = pageModel.getPageSaveKey();
			num[i - 1] = pageModel.getPageNo();// 获取在当前文件中的页码值
		}

		boolean flag = convertToPdf.composePdf(path, absolutePath, pagesId, pageSaveKey, num);
		return null;
	}

	@Override
	public int whetherAddFav(HttpServletRequest request, Long pageId) {
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		int flag = pageDao.whetherAddFav(user.getUserId(), pageId);
		return flag;
	}

	@Override
	public void saveCollection(int flag, Long pageId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		int fav = whetherAddFav(request, pageId);
		if (flag == 1) {// 收藏页面
			if (fav == 0) {
				System.out.println("fav==0");
				// 收藏
				pageDao.saveCollection(fav, pageId, user.getUserId());
			}
		} else {// 取消收藏
			if (fav == 1) {// 已收藏，取消收藏
				System.out.println("fav==1");
				pageDao.saveCollection(fav, pageId, user.getUserId());
			}

		}
	}

	@Override
	public List<PageModel> getPageByKeyWord(String keyword) {
		ArrayList<String> list = ExcuteQuery.query(keyword);// 获得到pageModel的saveKey
		ArrayList<PageModel> pages = new ArrayList<PageModel>();
		for (int i = 0; i < list.size(); ++i) {
			PageModel pageModelBySaveKey = getPageModelBySaveKey(list.get(i));
			pages.add(pageModelBySaveKey);
		}
		return pages;
	}

	@Override
	public PageModel getPageModelBySaveKey(String pageSaveKey) {
		PageModel pageModel = pageDao.getpageModelBySaveKey(pageSaveKey);
		return pageModel;
	}

}
