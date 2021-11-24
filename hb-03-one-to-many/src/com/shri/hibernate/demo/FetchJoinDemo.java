package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//create the objects
//			Instructor tempInstructor = 
//					new Instructor("Chad", "Darby", "darby@luv2code.com");
//			InstructorDetail tempInstructorDetail = 
//					new InstructorDetail("https://luv2code.com/youtube", "Luv 2 code!!");
			
			//begin the transaction
			session.beginTransaction();
			
			int theId = 1;
			
			try {
			
				//option2: Hibernate query with HQL
				
				Query<Instructor> query = session.createQuery("select i from Instructor i "
									+ "Join Fetch i.courses "
									+ "where i.id = :theInstructorId", Instructor.class);
				
				//set the parameter for the query
				query.setParameter("theInstructorId", theId);
				
				//execute query and get instructor
				Instructor tempInstructor = query.getSingleResult();
				
				System.out.println("lv2code: Instructor: "+tempInstructor);
				
				//commit the transaction
				session.getTransaction().commit();
				
				//close the session
				session.close();
				
				System.out.println("\nSession is closed now!\n");
				
				//since courses are lazily loaded this should fail
				
				//solution: option 2:
				
				//get the courses for the instructor
				System.out.println("lv2code: Courses: "+tempInstructor.getCourses());
				
				System.out.println("Done!");
			}
			catch(NullPointerException e) {
				e.printStackTrace();
			}
			
		}
		finally {
			
			//add clean up code
			session.close();
			
			sessionFactory.close();
			
		}
	}

}
