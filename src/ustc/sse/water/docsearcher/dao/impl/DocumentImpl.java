package ustc.sse.water.docsearcher.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.DocumentDao;
import ustc.sse.water.docsearcher.model.DocumentModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月1日 下午4:17:47 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月1日 下午4:17:47
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Service("documentDao")
public class DocumentImpl implements DocumentDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Long save(DocumentModel documentModel) {
		Session session = getCurrentSession();
		System.out.println("文档保存" + documentModel.getDocTitle());
		long documentId = (Long) session.save(documentModel);
		System.out.println("文档保存的id" + documentId);
		session.flush();
		return documentId;

	}

	@Override
	public DocumentModel find(Long documentId) {

		Session session = getCurrentSession();
		String hql = "select d from DocumentModel d where d.docId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, documentId);
		List<DocumentModel> list = query.list();
		int size = list.size();
		return list.get(0);

	}

	@Override
	public void update(DocumentModel documentModel) {
		Session session = getCurrentSession();
		System.out.println("文档页数保存id" + documentModel.getDocId());
		session.update(documentModel);
		session.flush();
		System.out.println("更新完成");

	}

	@Override
	public Long getDocumentsByTags(Long tagId) {
		Session session = getCurrentSession();
		String hql = "select d from DocumentModel d where d.tagId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, tagId);
		List<DocumentModel> list = query.list();
		long size = list.size();
		return size;
	}

	@Override
	public DocumentModel getDocumentsByDocId(Long docId) {
		Session session = getCurrentSession();
		String hql = "select d from DocumentModel d where d.docId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, docId);
		List<DocumentModel> list = query.list();

		return list.get(0);
	}

	@Override
	public List<DocumentModel> searchSlides(String keyword) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		String hql = "select d from DocumentModel d where d.docTitle like ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, "%" + keyword.trim() + "%");
		List<DocumentModel> list = query.list();
		return list;
	}

}
