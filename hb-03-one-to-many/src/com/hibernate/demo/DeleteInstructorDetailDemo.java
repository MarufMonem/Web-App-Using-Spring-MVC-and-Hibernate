package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			int theId=10;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			if (tempInstructorDetail != null) {
				
				//print the instructor detail
				System.out.println("Delete  instructor detail is : " + tempInstructorDetail);
				//print the associated instructor
				System.out.println("Delete the associated instructor is: " + tempInstructorDetail.getInstructor());
				
				//remove the associated object reference
				//break bi-directional link
				tempInstructorDetail.getInstructor().setInstructorDetail(null); //this is saying that now you dont have any references to any details.
				
				//delete 
				session.delete(tempInstructorDetail);
				
				//commit transaction
				session.getTransaction().commit();
				System.out.println("done");
			}else {
				System.out.println("It was null");
			}

			
		} finally {
			session.close(); //handles connection leak
			factory.close();
		}
	}

}
