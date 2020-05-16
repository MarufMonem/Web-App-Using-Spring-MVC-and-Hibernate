package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Get complete: " + myStudent);
			
			System.out.println("Deleting ... ");
//			session.delete(myStudent);
//			session.getTransaction().commit();
			
			//Alternate approach
			session.createQuery("delete from Student where id=2").executeUpdate();
						
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
