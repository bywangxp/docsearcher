package ustc.sse.water.docsearcher.service.ebo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.TagDao;
import ustc.sse.water.docsearcher.model.TagModel;
import ustc.sse.water.docsearcher.service.ebi.TagEbi;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月18日 上午1:09:19 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月18日 上午1:09:19
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Service("tagEbi")
public class TagEbo implements TagEbi {

	@Resource
	private TagDao tagDao;

	@Override
	public List<TagModel> getAllTags() {

		return tagDao.getAllTags();
	}

	@Override
	public TagModel getTagsByTagId(Long tagId) {
		// TODO Auto-generated method stub
		return tagDao.getTagByTagId(tagId);
	}

}
