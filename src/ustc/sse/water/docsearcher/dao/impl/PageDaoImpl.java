package ustc.sse.water.docsearcher.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ustc.sse.water.docsearcher.dao.dao.PageDao;
import ustc.sse.water.docsearcher.model.PageModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月5日 下午8:42:30 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月5日 下午8:42:30
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Repository("pageDao")
public class PageDaoImpl implements PageDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void savePageModel(PageModel pageModel) {
		Session session = getCurrentSession();
		long save = (Long) session.save(pageModel);
		session.flush();

	}

	@Override
	public PageModel getPageByPageId(long pageid) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		String hql = "select p from PageModel p where p.pageId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, pageid);// Integer需要转为Model实际的；类型
		List<PageModel> list = query.list();
		PageModel pageModel = list.get(0);
		return pageModel;
	}

	@Override
	public List<PageModel> getPageListByDocId(Long docId) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		String hql = "select p from PageModel p where p.docId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, docId);
		List<PageModel> list = query.list();
		int size = list.size();
		return list;
	}

}
