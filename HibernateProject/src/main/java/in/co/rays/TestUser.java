package in.co.rays;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class TestUser {
	public static void main(String[] args) {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testGet();
		// testlist();
		// testColumns();
		// testCriteria();
		// testProjections();
		// testprojRest();
		// testcritOrderby();
		// testChache();
		// testSecondlevel();
		// testMerge();
		testNamedQuery();
	}

	private static void testAdd() {
		User us = new User();
		us.setFname("Sandeep");
		us.setLname("Mahajan");
		us.setUsername("Sandeep");
		us.setPassword("11222");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();

		Transaction tx = s.beginTransaction();

		s.save(us);

		tx.commit();

		s.close();

		System.out.println("inserted");
	}

	public static void testUpdate() {

		User us = new User();
		us.setId(3);
		us.setFname("Sunny");
		us.setLname("Sharma");
		us.setUsername("sunny@");
		us.setPassword("12345");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		Transaction tx = s.beginTransaction();

		s.update(us);

		tx.commit();

		s.close();

	}

	public static void testGet() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		User us = (User) s.get(User.class, 3);
		System.out.println(us.getFname());
		System.out.println(us.getLname());
		System.out.println(us.getUsername());
		System.out.println(us.getPassword());

		s.close();

	}

	public static void testDelete() {
		User us = new User();
		us.setId(4);

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		Transaction tx = s.beginTransaction();

		s.delete(us);
		tx.commit();
		s.close();
		System.out.println("deleted");
	}

	public static void testlist() {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		// Query q = s.createQuery("from User");// HQL
		Query q = s.createQuery("from User where id = 1");// HQL
		Query q1 = s.createQuery("from User where fname like '%Ajay%'");// HQL

		List<User> l = q.list();
		Iterator<User> it = l.iterator();

		while (it.hasNext()) {
			User user = (User) it.next();
			System.out.println(user.getId() + "\t" + user.getFname() + "\t" + user.getLname());

		}
	}

	public static void testColumns() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		Query q = s.createQuery("select u.id,u.fname from User u");

		List list = q.list();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			Object[] user = (Object[]) it.next();
			System.out.println(user[0]);
			System.out.println(user[1]);

		}
	}

	public static void testCriteria() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		Criteria c = s.createCriteria(User.class);
		c.add(Restrictions.eq("id", 1));
		c.add(Restrictions.like("lname", "%sharma%"));
		List<User> l = c.list();
		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			User user = it.next();
			System.out.println(user.getId());
			System.out.println(user.getFname());
			System.out.println(user.getLname());
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());

		}
	}

	public static void testProjections() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		Criteria c = s.createCriteria(User.class);

		ProjectionList p = Projections.projectionList();
		// p.add(Projections.property("id"));

		// p.add(Projections.property("fname"));
		p.add(Projections.rowCount());
		// p.add(Projections.groupProperty("fname"));

		c.setProjection(p);
		List list = c.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			int user = (Integer) it.next();

			// Object[] user = (Object[]) it.next();
			System.out.println(user);

			// System.out.println(user[1]);
		}
	}

	public static void testprojRest() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		Criteria c = s.createCriteria(User.class);

		c.add(Restrictions.or(Restrictions.eq("id", 1), Restrictions.like("lname", "%sharma%")));

		ProjectionList p = Projections.projectionList();

		p.add(Projections.property("fname"));
		p.add(Projections.property("lname"));
		c.setProjection(p);
		List list = c.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {

			Object[] user = (Object[]) it.next();
			System.out.println(user[0]);

			System.out.println(user[1]);

		}
	}

	public static void testcritOrderby() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		Criteria c = s.createCriteria(User.class);
		c.addOrder(Order.asc("fname"));

		List list = c.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			User u = new User();

			u = (User) it.next();
			System.out.println(u.getFname());

		}
	}

	public static void testChache() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		User us = (User) s.get(User.class, 2);
		System.out.println(us.getFname());
		System.out.println(us.getLname());
		System.out.println(us.getUsername());
		System.out.println(us.getPassword());

		s.close();
		// s.evict(us);
		// s.clear();
		User us1 = (User) s.get(User.class, 2);
		System.out.println(us.getFname());
		System.out.println(us.getLname());
		System.out.println(us.getUsername());
		System.out.println(us.getPassword());
		s.close();
	}

	public static void testSecondlevel() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		User us = (User) s.get(User.class, 2);
		System.out.println(us.getFname());
		System.out.println(us.getLname());
		System.out.println(us.getUsername());
		System.out.println(us.getPassword());

		s.close();
		sf.close();
		// s.evict(us);
		// s.clear();
		SessionFactory sf1 = new Configuration().configure().buildSessionFactory();
		Session s1 = sf1.openSession();
		User us1 = (User) s1.get(User.class, 2);
		System.out.println(us.getFname());
		System.out.println(us.getLname());
		System.out.println(us.getUsername());
		System.out.println(us.getPassword());

		s1.close();
		sf1.close();
	}

	public static void testMerge() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();

		User us = (User) s.get(User.class, 3);
		System.out.println(us.getFname());
		s.close();
		us.setFname("Dheeraj");

		Session s1 = sf.openSession();
		Transaction tx = s1.beginTransaction();
		System.out.println("inserted");
		s1.merge(us);
		tx.commit();
	}

	public static void testNamedQuery() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session s = sf.openSession();
		
		Query q = s.getNamedQuery("allUser");

		List<User> l = q.list();
		Iterator<User> it = l.iterator();

		while (it.hasNext()) {
			User user = (User) it.next();
			System.out.println(user.getId() + "\t" + user.getFname() + "\t" + user.getLname());

		}
}
}