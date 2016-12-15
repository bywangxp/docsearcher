package ustc.sse.water.docsearcher.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("testDAO")
public class TestDAO {
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Test findOne(final int id) {
		return (Test) getCurrentSession().get(Test.class, id);
	}
	
}
