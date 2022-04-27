package in.co.rays;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.JoinSequence.Join;

public class TestDept {

	public static void main(String[] args) {
		// testadd();
		testJoin();
	}

	private static void testadd() {
		Department dp = new Department();
		dp.setDepart("HR");

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();

		Transaction tx = s.beginTransaction();

		s.save(dp);

		tx.commit();

		s.close();

		System.out.println("Inserted");

	}

	public static void testJoin() {

		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();

		List c = (List) s.createCriteria(User.class).setFetchMode("Department", FetchMode.JOIN)
				.add(Restrictions.eq("dept_id", 1)).list();

		Iterator it = c.iterator();
		while (it.hasNext()) {
			User d = (User) it.next();
			System.out.println(d.getDept_id());
			System.out.println(d.getFname());

			s.close();
		}

	}
}
