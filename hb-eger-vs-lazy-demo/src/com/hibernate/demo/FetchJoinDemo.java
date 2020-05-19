package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

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
			
			//option 2: Query with HQL
			
			//get instructor from db
			int myId=1;
			Query<Instructor> query = session.createQuery("select i from Instructor i JOIN FETCH i.courses where i.id=:theInstructorId", Instructor.class);
						
			//set parameter on query
			query.setParameter("theInstructorId", myId);
			
//			execute the query
			Instructor tempInstructor = query.getSingleResult();
					
			System.out.println("ANIK: The instructor is: " + tempInstructor);

			//commit transaction
			session.getTransaction().commit();
			
			session.close();
			System.out.println("Session closed");
			System.out.println("ANIK: Takes the following courses: " + tempInstructor.getCourses());
			System.out.println("ANIK: done");
			
		} finally {
			session.close();
			factory.close();
		}
	}

}
