package com.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDButil {
	
	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	private static boolean isSuccess= false;
	

	public static List<Customer> validate (String userName, String password){
		
		ArrayList<Customer> cus = new ArrayList<>();
		
		//validate
		try {			
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();
			String sql = "select * from customer where username ='"+userName+"' and password ='"+password+"'";
			rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String userU = rs.getString(5);
				String passU = rs.getString(6);

				Customer c = new Customer(id,name,email,phone,userU,passU);
				cus.add(c);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cus;
	}
	
	public static boolean insertCustomer(String name,String email,String phone,String username,String password) {
		boolean isSuccess= false;
		
		
		try {
			
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();
			String sql = "insert into customer values(0,'"+name+"','"+email+"','"+phone+"','"+username+"','"+password+"')";
			int rs =stmt.executeUpdate(sql);
			
			if(rs > 0) {
				isSuccess = true;
			}else {
				isSuccess =false;
			}
			
			System.out.println(isSuccess);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
		
	}
	
	public static boolean updatecustomer(String id,String name,String email,String phone,String username,String password) {
		
		try {
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();
			
			String sql = "update customer set name='"+name+"',email='"+email+"',phone='"+phone+"',username='"+username+"',password='"+password+"'where id='"+id+"'";
			int rs =stmt.executeUpdate(sql);
			
			if(rs > 0) {
				isSuccess = true;
			}else {
				isSuccess =false;
			}
			
			System.out.println(isSuccess);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	public static List<Customer> getCustomerDetails(String Id){
		
		int CovertedId =Integer.parseInt(Id);
		ArrayList<Customer> cus = new ArrayList<>();
		
		try {			
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();
			String sql = "select * from customer where id ='"+CovertedId+"'";
			rs=stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String userU = rs.getString(5);
				String passU = rs.getString(6);

				Customer c = new Customer(id,name,email,phone,userU,passU);
				cus.add(c);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return cus;
	}
	
	public static boolean deletecustomer(String Id) {
		int CovertedId =Integer.parseInt(Id);

		try {
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();
			
			String sql = "delete from customer where id='"+CovertedId+"'";
			int rs =stmt.executeUpdate(sql);
			
			if(rs > 0) {
				isSuccess = true;
			}else {
				isSuccess =false;
			}
			
			System.out.println(isSuccess);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
}

