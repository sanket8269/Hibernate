package in.co.rays;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestO2M {
	public static void main(String[] args) {

		/*
		 * AuctionItem item = new AuctionItem(); item.setDescreptions("iphone");
		 * 
		 * Bid bid1 = new Bid(); bid1.setAmount(20000);
		 * 
		 * Bid bid2 = new Bid(); bid2.setAmount(16000);
		 * 
		 * Bid bid3 = new Bid(); bid3.setAmount(18000);
		 * 
		 * Bid bid4 = new Bid(); bid4.setAmount(20000);
		 * 
		 * Set<Bid> set = new LinkedHashSet<Bid>();
		 * 
		 * set.add(bid1); set.add(bid2); set.add(bid3); set.add(bid4);
		 * 
		 * item.setBids(set);
		 * 
		 * SessionFactory sf = new Configuration().configure().buildSessionFactory();
		 * 
		 * Session s = sf.openSession();
		 * 
		 * Transaction tx = s.beginTransaction();
		 * 
		 * s.save(item);
		 * 
		 * tx.commit();
		 * 
		 * s.close();
		 * 
		 * System.out.println("One to Many Done");
		 */
		SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Session s = sf.openSession();

		AuctionItem item = (AuctionItem) s.get(AuctionItem.class, 1);
		Set<Bid> bids = item.getBids();

		Iterator it = bids.iterator();
		while (it.hasNext()) {
			Bid B = (Bid) it.next();
			System.out.println(B.getAmount());
			System.out.println(item.getDescreptions());

		}

	}

}
