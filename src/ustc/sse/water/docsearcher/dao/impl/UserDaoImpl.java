package ustc.sse.water.docsearcher.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import ustc.sse.water.docsearcher.dao.dao.UserDao;
import ustc.sse.water.docsearcher.model.DocumentModel;
import ustc.sse.water.docsearcher.model.PageModel;
import ustc.sse.water.docsearcher.model.UserModel;

/**
 * 
 * 类型名 <br>
 * 功能描述
 * <p>
 * 修改历史 2016年10月30日 下午10:47:24 修改人 <br>
 * 修改说明 <br>
 * <p>
 * Copyright: Copyright (c) 2016年10月30日 下午10:47:24
 * <p>
 * Company: 中科大软件学院
 * <p>
 * 
 * @author 王训谱 bywangxp@mail.ustc.edu.cn
 * @version 版本号
 */
@Service("userDao")
public class UserDaoImpl implements UserDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public UserModel find(UserModel userModel) {
		Session session = getCurrentSession();
		// Transaction tx = session.beginTransaction();
		String hql = "select u from UserModel u where u.userName = ? and u.userPassword=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userModel.getUserName());
		query.setParameter(1, userModel.getUserPassword());

		List list = query.list();

		int size = list.size();

		/*
		 * System.out.println(userQueryModel.getUserName() + "" +
		 * userQueryModel.getUserPassword()); DetachedCriteria dc =
		 * DetachedCriteria.forClass(UserQueryModel.class);
		 * dc.add(Restrictions.eq("userName", userQueryModel.getUserName()));
		 * dc.add(Restrictions.eq("password",
		 * userQueryModel.getUserPassword())); Criteria cri =
		 * dc.getExecutableCriteria(session); List<UserQueryModel> list =
		 * cri.list(); int size = list.size();
		 */
		session.flush();

		// tx.commit();

		if (size != 0) {
			userModel = (UserModel) list.get(0);
			System.out.println(userModel.getUserName());
			System.out.println("确有此人");
			return userModel;
		} else {
			System.out.println("查无此人");

			return null;
		}
	}

	// 该方法与DocmentModel方法冗余，后期要做调整
	@Override
	public DocumentModel getDocument(Long id) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		String hql = "select d from DocumentModel d where d.docId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<DocumentModel> list = query.list();
		int size = list.size();
		return list.get(0);
	}

	@Override
	public List<PageModel> getPage(Long docId) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		String hql = "select p from PageModel p where p.docId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, docId);
		List<PageModel> list = query.list();
		int size = list.size();
		return list;
	}

	@Override
	public UserModel getUserById(Long userId) {
		// TODO Auto-generated method stub
		Session session = getCurrentSession();
		String hql = "select u from UserModel u where u.userId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		List<UserModel> list = query.list();
		return list.get(0);
	}

}
