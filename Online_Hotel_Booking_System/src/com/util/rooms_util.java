package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.rooms;

public class rooms_util {
	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	
	public static List<rooms> getAllrooms(){
	
	ArrayList<rooms> rooms = new ArrayList<>();
	
	try {			
		con = DBconnect.getConnection()		;
		stmt = con.createStatement();
		String sql = "select * from rooms" ;
		rs=stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			rooms room = new rooms(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDouble(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11)
            );
            rooms.add(room);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	return rooms;
	}
	
	public static List<rooms> getFilteredroom1(String roomType,String acType){
		ArrayList<rooms> frooms = new ArrayList<>();
		
		try {			
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();
			String sql = "select * from rooms where r_type ='"+roomType+"'and ac_type ='"+acType+"'" ;
			rs=stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				rooms room = new rooms(
	                    rs.getInt(1),
	                    rs.getString(2),
	                    rs.getString(3),
	                    rs.getInt(4),
	                    rs.getDouble(5),
	                    rs.getString(6),
	                    rs.getString(7),
	                    rs.getString(8),
	                    rs.getString(9),
	                    rs.getString(10),
	                    rs.getString(11)
	            );
	            frooms.add(room);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return frooms;
		}
	
public static List<rooms> getFilteredroom2(String roomType){
	ArrayList<rooms> frooms = new ArrayList<>();
	
	try {			
		con = DBconnect.getConnection()		;
		stmt = con.createStatement();
		String sql = "select * from rooms where r_type ='"+roomType+"'" ;
		rs=stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			rooms room = new rooms(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDouble(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11)
            );
            frooms.add(room);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	return frooms;
	}

public static List<rooms> getFilteredroom3(String acType){
	ArrayList<rooms> frooms = new ArrayList<>();
	
	try {			
		con = DBconnect.getConnection()		;
		stmt = con.createStatement();
		String sql = "select * from rooms where ac_type ='"+acType+"'" ;
		rs=stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			rooms room = new rooms(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getDouble(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11)
            );
            frooms.add(room);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	return frooms;
	}
}
