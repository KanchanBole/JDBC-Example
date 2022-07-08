package com.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.app.model.Customer;


public class CURDTest {

	
		
		public static void selectAll() {
		
			Connection con=null;
			List<Customer> customerList=new ArrayList<Customer>();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_seven_batch","root","root");
	             
				Statement smt=con.createStatement();
				
				ResultSet rs=smt.executeQuery("select * from customer");
				
				while (rs.next()) {
					Customer customer=new Customer();
					customer.setId(rs.getInt("id"));
					customer.setName(rs.getString("name"));
					customer.setMobile(rs.getString("mobile"));
					customer.setAddress(rs.getString("address"));	
			      	customerList.add(customer);
				}
				
	       } catch (ClassNotFoundException | SQLException e) {
		 		e.printStackTrace();
	       }
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		customerList.stream().forEach(System.out::println);
		
	}

	public static void saveCustomers() {	
		
		Connection con = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("How many customers do u want to add");
		int nc = sc.nextInt();
		for (int i = 0; i < nc; i++) {
			Customer customer = new Customer();
			System.out.println("Enter Name :");
			customer.setName(sc.next());
			
			System.out.println("Enter Mobile :");
            customer.setMobile(sc.next());
            
            System.out.println("Enter Address :");
			customer.setAddress(sc.next());
            try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 

				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_seven_batch", "root", "root");
				Statement smt = con.createStatement(); 

				int result = smt.executeUpdate("insert into customer(name,mobile,address)values('" + customer.getName()
						+ "','" + customer.getMobile() + "','" + customer.getAddress() + "')");
				if (result > 0) {
					System.out.println("Successfully saved");
				} else {
					System.out.println("failure");
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
		
	public static void main(String[] args) {
		
		saveCustomers();
		selectAll();
		
	}		
}
