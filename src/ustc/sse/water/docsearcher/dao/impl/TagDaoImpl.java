package ustc.sse.water.docsearcher.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.TagDao;
import ustc.sse.water.docsearcher.model.TagModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年11月5日 下午5:39:52 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年11月5日 下午5:39:52
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Service("tagDao")
public class TagDaoImpl implements TagDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<TagModel> getAllTags() {

		Session session = getCurrentSession();
		// Transaction tx = session.beginTransaction();
		String hql = "select t from TagModel t";
		Query query = session.createQuery(hql);
		List<TagModel> list = query.list();
		return list;
	}

}
