package ustc.sse.water.docsearcher.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ustc.sse.water.docsearcher.dao.dao.GlobalDao;
import ustc.sse.water.docsearcher.model.DownloadModel;
import ustc.sse.water.docsearcher.model.DownloadRankModel;
import ustc.sse.water.docsearcher.model.SearchRecordModel;
import ustc.sse.water.docsearcher.model.TagRecordModel;

@Repository("globalDao")
public class GlobalDaoImpl implements GlobalDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public ArrayList<DownloadRankModel> getDownloadRankModel() {
		Session session = getCurrentSession();
		Criteria add = session.createCriteria(DownloadRankModel.class).add(Restrictions.between("downRank", 1, 5));
		List<DownloadRankModel> list = add.list();
		System.out.println("结果集大小" + list.size());
		return (ArrayList<DownloadRankModel>) list;
	}

	// 获取标签记录，标签记录定期统计
	@Override
	public List<TagRecordModel> getTagRecordModel() {
		Session session = getCurrentSession();
		String hql = " from TagRecordModel t order by t.recordTime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(8);
		List<TagRecordModel> list = query.list();
		return list;
	}

	@Override
	public void saveDocumentRecord(DownloadModel downloadModel) {
		Session session = getCurrentSession();
		long downloadModelId = (Long) session.save(downloadModel);
		System.out.println("下载记录保存的id" + downloadModelId);
		session.flush();

	}

	@Override
	public void saveSearcherRecord(SearchRecordModel searchRecord) {
		Session session = getCurrentSession();
		long searchRecordId = (Long) session.save(searchRecord);
		System.out.println("搜素记录保存的id" + searchRecordId);
		session.flush();
	}
}
