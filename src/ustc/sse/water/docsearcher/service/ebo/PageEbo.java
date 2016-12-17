package ustc.sse.water.docsearcher.service.ebo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.PageDao;
import ustc.sse.water.docsearcher.model.PageModel;
import ustc.sse.water.docsearcher.service.ebi.PageEbi;

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

}
