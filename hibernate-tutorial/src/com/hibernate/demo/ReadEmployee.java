package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.demo.entity.Employee;
import com.hibernate.demo.entity.Student;

public class ReadEmployee {

	public static void main(String[] args) {
		//create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//get a new session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			int employeeId=2;
			
			System.out.println("Getting student with id: " +employeeId);
			
			Employee myEmployee = session.get(Employee.class, employeeId);
			
			System.out.println("Get complete: " + myEmployee);
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			
			
			System.out.println("done");
		} finally {
			factory.close();
		}
		

	}

}
