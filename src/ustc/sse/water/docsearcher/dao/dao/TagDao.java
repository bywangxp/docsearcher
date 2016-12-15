package ustc.sse.water.docsearcher.dao.dao;

import java.util.List;

import ustc.sse.water.docsearcher.model.TagModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月5日 下午8:41:56 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月5日 下午8:41:56
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
public interface TagDao {

	List<TagModel> getAllTags();

}
