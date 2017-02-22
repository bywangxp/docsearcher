package ustc.sse.water.docsearcher.dao.dao;

import java.util.List;

import ustc.sse.water.docsearcher.model.PageModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月5日 下午5:41:37 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月5日 下午5:41:37
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
public interface PageDao {

	void savePageModel(PageModel pageModel);

	PageModel getPageByPageId(long pageid);

	List<PageModel> getPageListByDocId(Long docId);

	int whetherAddFav(Long userId, Long pageId);

	void saveCollection(int fav, Long pageId, Long userId);

	PageModel getpageModelBySaveKey(String pageSaveKey);

	// List<PageModel> getPageByUserId(Long userId);

}
