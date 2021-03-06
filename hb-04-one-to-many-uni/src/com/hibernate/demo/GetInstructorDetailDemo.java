package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {

			//start a transaction
			session.beginTransaction();

			//get the instructor detail object
			int theId=4;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("The instructor detail is : " + tempInstructorDetail);
			//print the associated instructor
			System.out.println("The associated instructor is: " + tempInstructorDetail.getInstructor());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			session.close(); //handles connection leak
			factory.close();
		}
	}

}
