package ustc.sse.water.docsearcher.service.ebo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.GlobalDao;
import ustc.sse.water.docsearcher.model.DownloadModel;
import ustc.sse.water.docsearcher.model.DownloadRankModel;
import ustc.sse.water.docsearcher.model.SearchRecordModel;
import ustc.sse.water.docsearcher.model.TagRecordModel;
import ustc.sse.water.docsearcher.model.UserModel;
import ustc.sse.water.docsearcher.service.ebi.GlobalEbi;
import ustc.sse.water.docsearcher.util.constant.PublicConstants;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月18日 上午1:09:07 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月18日 上午1:09:07
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Service("globalEbi")
public class GlobalEbo implements GlobalEbi {

	@Resource
	private GlobalDao globalDao;

	@Override
	public ArrayList<DownloadRankModel> getDownloadRankModel() {
		return globalDao.getDownloadRankModel();

	}

	@Override
	public List<TagRecordModel> getTagRecordModel() {

		return globalDao.getTagRecordModel();

	}

	@Override
	public void saveDownLoadRecord(HttpServletRequest request, long[] pagesId) {
		HttpSession session = request.getSession();
		UserModel userModel = (UserModel) session.getAttribute("user");
		Long userId = userModel.getUserId();

		for (long l : pagesId) {
			DownloadModel downloadModel = new DownloadModel();
			downloadModel.setDownloadTime(new Date());
			downloadModel.setUserId(userId);
			downloadModel.setPageId(l);
			globalDao.saveDocumentRecord(downloadModel);
		}

	}

	@Override
	public void saveSearchRecord(HttpServletRequest request, String keyword) {
		HttpSession session = request.getSession();
		UserModel userModel = (UserModel) session.getAttribute("user");
		Long userId = userModel.getUserId();
		// 保存搜素记录
		SearchRecordModel searchRecord = new SearchRecordModel();
		searchRecord.setUserId(userId);
		searchRecord.setSearchKeyword(keyword);
		searchRecord.setSearchTime(new Date());
		searchRecord.setSearchLogo(PublicConstants.SEARCH_RECORD_LOGO);
		globalDao.saveSearcherRecord(searchRecord);

	}
}
