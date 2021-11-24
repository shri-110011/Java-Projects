package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;
import com.shri.hibernate.demo.entity.Review;
import com.shri.hibernate.demo.entity.Student;

public class DeleteCoursesForMaryDemo {

	public static void main(String[] args) {

		//create the session factory
		SessionFactory sessionFactory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Instructor.class)
											.addAnnotatedClass(InstructorDetail.class)
											.addAnnotatedClass(Course.class)
											.addAnnotatedClass(Review.class)
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();
		
		//create the session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			
			//begin the transaction
			session.beginTransaction();
			
			//get the student from the db
			int theId = 2;
			
			Student tempStudent = session.get(Student.class, theId);
			
			System.out.println("\nLoaded student: "+tempStudent);
			System.out.println("Couses: "+tempStudent.getCourses());
			
			//delete the student
			session.delete(tempStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		}
		finally {
			
			//add clean up code
			session.close();
			
			sessionFactory.close();
			
		}
	}

}
