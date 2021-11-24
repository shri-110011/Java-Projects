package com.shri.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shri.hibernate.demo.entity.Course;
import com.shri.hibernate.demo.entity.Instructor;
import com.shri.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
			
			Instructor tempInstructor = 
					new Instructor("Susan", "Public", "susan@luv2code.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("https://www.youtube.com", "Video Games");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// Note: We can't create these Course objects and save along while saving Instructor object
			// because while saving the Instructor, this Instructor object along with its associated
			// object(InstructorDetail) will be stored since instructor_detail_id is the foreign key
			// in instructor table and associates instructor to instructor_detail.
			
			//begin the transaction
			session.beginTransaction();
			
			//save the objects
			//Note: This will also save InstructorDetail object because cascade type is All
			System.out.println("Saving the instructor: "+tempInstructor);
			session.save(tempInstructor);
			
			
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
