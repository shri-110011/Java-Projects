package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//My New Code
			
			int studentId = 1;
			
			//now get a new session object and start the transaction
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
//			System.out.println("\nGetting student with id: "+studentId);
			
//			Student myStudent = session.get(Student.class, studentId);
			
//			session.delete(myStudent);
			
			session.createQuery("delete from Student where id = 2").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			sessionFactory.close();
		}
	}

}
