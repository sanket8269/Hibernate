package in.co.rays;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestCriteria {
	public static void main(String[] args) throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<User> query = builder.createQuery(User.class);

		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("id"), 1));

		Query q = session.createQuery(query);

		List<User> user = q.getResultList();

		Iterator<User> list = user.iterator();

		while (list.hasNext()) {
			User u = (User) list.next();
			System.out.println(u.getId());
			System.out.println(u.getFirstName());
			System.out.println(u.getLastName());
		}

	}
}
