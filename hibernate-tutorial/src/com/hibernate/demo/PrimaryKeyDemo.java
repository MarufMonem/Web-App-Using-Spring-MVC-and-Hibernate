package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//create a session factory
				SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
				
				//create a session
				Session session = factory.getCurrentSession();
				
				try {
					
					//create 3 student object
					System.out.println("Craeting a new student object");
					Student tempStudent1 = new Student("Maruf", "Monem", "Anik@yahoo.com");
					Student tempStudent2= new Student("Sanjida", "Monem", "MimDIm@yahoo.com");
					Student tempStudent3 = new Student("Alif", "Ahmad", "Alif@yahoo.com");
							
					//start a transaction
					session.beginTransaction();
					
					//save the student object
					System.out.println("Saving the student");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					
					
					//commit transaction
					session.getTransaction().commit();
					System.out.println("done");
				} finally {
					factory.close();
				}
				
	}

}
