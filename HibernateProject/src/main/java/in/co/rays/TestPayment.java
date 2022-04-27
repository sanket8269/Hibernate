package in.co.rays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestPayment {
public static void main(String[] args) {

	Cheque ch = new Cheque();
	ch.setId(1);
	ch.setAmount(1000);
	ch.setCheNumber(11111);
	
	CreditCard cr = new CreditCard();
	cr.setId(2);
	cr.setAmount(900);
	cr.setCcType("VISA");
	

	SessionFactory sf = new Configuration().configure().buildSessionFactory();

	Session s = sf.openSession();

	Transaction tx = s.beginTransaction();

	s.save(ch);
	s.save(cr);

	tx.commit();

	s.close();

	System.out.println("inserted");

}
}
