package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Testjdbc {

	public static void main(String[] args) {

		String jdbcUrl="jdbc:mysql://localhost:3306/hb_student_tracker?userSSL=flase&serverTimezone=UTC";
		String user="hbstudent";
		String pass = "hbstudent";
		try {
			System.out.println("Connecting to DB... " + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl,user,pass);
			
			System.out.println("Successful!!!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
