package in.co.rays;

import java.nio.file.attribute.AclEntry.Builder;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TestUser {
	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testGet();
		// testListCriteria();
		// testSingleColumn();
		testMultiColumn();

	}

	private static void testAdd() throws Exception {
		User u = new User();
		u.setFirstName("Dheeraj");
		u.setLastName("Haryani");

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		s.save(u);
		tx.commit();
		s.close();
		HibernateUtil.shutdown();
	}

	public static void testUpdate() throws Exception {
		User user = new User();
		user.setId(1);
		user.setFirstName("Jay");
		user.setLastName("Chouhan");

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		System.out.println("Updated Successfully");
		tx.commit();
		session.close();
		HibernateUtil.shutdown();
	}

	public static void testDelete() throws Exception {
		User user = new User();
		user.setId(3);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		System.out.println("deleted");
		tx.commit();
		session.close();
		HibernateUtil.shutdown();

	}

	public static void testGet() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		User u = (User) session.get(User.class, 1);
		System.out.println(u.getId());
		System.out.println(u.getFirstName());
		System.out.println(u.getLastName());
		session.close();
		HibernateUtil.shutdown();
	}

	public static void testListCriteria() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> R = cq.from(User.class);
		cq.select(R);
		Query q = session.createQuery(cq);

		List<User> l = q.getResultList();

		Iterator<User> it = l.iterator();

		while (it.hasNext()) {
			User user = (User) it.next();
			System.out.println(user.getId());
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
		}
	}

	public static void testSingleColumn() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<User> R = cq.from(User.class);
		cq.multiselect(R.get("firstName"));
		Query q = session.createQuery(cq);

		List<String> l = q.getResultList();

		Iterator<String> it = l.iterator();

		while (it.hasNext()) {
			String string = (String) it.next();
			System.out.println(string);

		}
	}

	public static void testMultiColumn() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<User> R = cq.from(User.class);
		cq.multiselect(R.get("firstName"), R.get("lastName"));
		Query q = session.createQuery(cq);

		List<Object[]> l = q.getResultList();

		Iterator<Object[]> it = l.iterator();

		while (it.hasNext()) {
			Object[] objects = (Object[]) it.next();
			System.out.println(objects[0]);
			System.out.println(objects[1]);
		}
	}
	public static void testCount() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<User> R = cq.from(User.class);
		cq.multiselect(cb.count(R));
		
		Query q = session.createQuery(cq);
		Long l =(Long) q.getSingleResult();
		System.out.println(l);
		session.close();
		HibernateUtil.shutdown();
	}

}
