package ustc.sse.water.docsearcher.dao.dao;

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

	void save(PageModel pageModel);

	PageModel getPageByPageId(Integer pageid);

}
