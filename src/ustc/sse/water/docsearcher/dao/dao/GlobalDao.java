package ustc.sse.water.docsearcher.dao.dao;

import java.util.ArrayList;
import java.util.List;

import ustc.sse.water.docsearcher.model.DownloadModel;
import ustc.sse.water.docsearcher.model.DownloadRankModel;
import ustc.sse.water.docsearcher.model.SearchRecordModel;
import ustc.sse.water.docsearcher.model.TagRecordModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年12月18日 上午1:08:33 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年12月18日 上午1:08:33
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
public interface GlobalDao {

	ArrayList<DownloadRankModel> getDownloadRankModel();

	List<TagRecordModel> getTagRecordModel();

	void saveDocumentRecord(DownloadModel downloadModel);

	void saveSearcherRecord(SearchRecordModel searchRecord);

}
