package in.co.rays;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestEmp {
	public static void main(String[] args) {
		
		  Employee emp = new Employee(); emp.setFirstName("Sanket");
		  emp.setLastName("jain");
		  
		  Address add = new Address(); add.setCity("indore"); add.setState("MP");
		  add.setStreet("Kalindi Gold");
		  
		  emp.setEmpadd(add);
		  
		  SessionFactory sf = new Configuration().configure().buildSessionFactory();
		  
		  Session s = sf.openSession();
		  
		  Transaction tx = s.beginTransaction();
		  
		  s.save(emp);
		  
		  tx.commit();
		  
		  s.close();
		  
		  System.out.println("One to one Done");
		  
		 	
	
	}
}
