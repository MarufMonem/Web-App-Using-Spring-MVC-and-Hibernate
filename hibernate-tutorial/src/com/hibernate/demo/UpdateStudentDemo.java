package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
								
			int studentId=1;
			
			//get a new session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve the student based on the id: primary key
			System.out.println("Getting student with id: " +studentId);
			
			Student mystudent = session.get(Student.class, studentId);
			
			System.out.println("Get complete: " + mystudent);
			
			//updating
			System.out.println("Update");
			mystudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//new stuff
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//update email for all students
			System.out.println("Updating student");
			session.createQuery("update Student set email = 'foo@gmail.com' ").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
						
			
			System.out.println("done");
			
		
		} finally {
			factory.close();
		}
	}

}
