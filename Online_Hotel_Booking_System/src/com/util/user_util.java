package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.registered_user;

public class user_util {
	private static boolean isSuccess= false;
	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	
	public static boolean registerUser(String name,String email,String phone,String username,String password) {
		 String role = "User";
		try {
			
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();
			String sql = "insert into user values(0,'"+name+"','"+email+"','"+phone+"','"+username+"','"+password+"','"+role+"')";
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
	
       public static List<registered_user> validate (String userName, String password){
		
		ArrayList<registered_user> users = new ArrayList<>();
		
		try {
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();		
			String sql = "select * from user where username ='"+userName+"' and password ='"+password+"'";
			ResultSet rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String userU = rs.getString(5);
				String passU = rs.getString(6);
				String role = rs.getString(7);


				registered_user u = new registered_user(id,name,email,phone,userU,passU,role);
			    users.add(u);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
