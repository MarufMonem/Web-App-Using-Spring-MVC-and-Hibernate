package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {

			//start a transaction
			session.beginTransaction();
			
			//get instructor from db
			
			int myId=1;
			Instructor tempInstructor = session.get(Instructor.class, myId);
						
			System.out.println("The instructor is: " + tempInstructor);
			System.out.println("Takes the following courses: " + tempInstructor.getCourses());
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
