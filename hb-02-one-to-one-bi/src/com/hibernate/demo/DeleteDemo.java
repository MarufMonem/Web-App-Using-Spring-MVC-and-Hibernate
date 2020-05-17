package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

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

			//get the instructor using their primary key
			int theId=2;
			Instructor tempInstructor = session.get(Instructor.class, theId); //return the object, null if not found
			System.out.println("Found him : "  + tempInstructor );
			
			//delete the instructor
			if (tempInstructor != null) {
				System.out.println("Instructor found!! Going to delete him now");
				
				//session.createQuery("delete from Instructor where id=2").executeUpdate();
				//        OR
				session.delete(tempInstructor);
				//because of cascading the instructor details would also be deleted.
				
			}
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("done");
			
		} finally {
			factory.close();
		}
	}

}
