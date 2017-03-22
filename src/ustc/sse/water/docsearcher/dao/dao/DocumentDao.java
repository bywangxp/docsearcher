package ustc.sse.water.docsearcher.dao.dao;

import java.util.ArrayList;
import java.util.List;

import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.util.Pager;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月1日 下午4:17:40 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月1日 下午4:17:40
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */

public interface DocumentDao {

	Long saveDocument(DocumentModel documentModel);

	void updateDocument(DocumentModel documentModel);

	Long getDocumentNumberByTag(Long tagId);

	DocumentModel getDocumentByDocId(Long docId);

	List<DocumentModel> searchDocumentListByKeyword(String keyword);

	ArrayList<DocumentModel> getAllDocumentModel();

	ArrayList<DocumentModel> getDocumentByUserId(Long userId ,Pager pager);

}
