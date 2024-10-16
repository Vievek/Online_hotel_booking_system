package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		con = DBconnect.getConnection();
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
	}finally {
        try {
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
		}finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
	}finally {
        try {
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
	}finally {
        try {
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	return frooms;
	}


public static List<rooms> getSelectedRoom(int r_id){
	ArrayList<rooms> rooms  = new ArrayList<>();
	

	try {			
		con = DBconnect.getConnection()		;
		stmt = con.createStatement();
		String sql = "select * from rooms where  r_id='"+r_id+"'" ;
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
	}finally {
        try {
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	return rooms;
	}

public static List<rooms> gethighlyBookedrooms(){
	
	ArrayList<rooms> rooms = new ArrayList<>();
	
	try {			
		con = DBconnect.getConnection();
		stmt = con.createStatement();
		String sql = "SELECT r.*, COUNT(b.r_id) AS booking_count " +
                "FROM rooms r " +
                "LEFT JOIN booking b ON r.r_id = b.r_id " +
                "GROUP BY r.r_id " +
                "ORDER BY COUNT(b.r_id) DESC;";
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
	}finally {
        try {
            if (rs != null) rs.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	return rooms;
	}

public static List<rooms> RecentlySelectedRooms(int userId) {
    ArrayList<rooms> rooms = new ArrayList<>();
    
    PreparedStatement pstmt = null;
    

    try {
        con = DBconnect.getConnection();
        String sql = "SELECT r.* " +
                "FROM rooms r " +
                "JOIN user_room_interactions uri ON r.r_id = uri.room_id " +
                "JOIN registered_user ru ON ru.ru_id = uri.user_id " +
                "WHERE ru.ru_id = ? " +
                "ORDER BY uri.interaction_id DESC;";


        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, userId);  // Setting the user ID in the prepared statement
        rs = pstmt.executeQuery();

        while (rs.next()) {
        	rooms room = new rooms(
                rs.getInt(1),      // Assuming room ID
                rs.getString(2),   // Assuming room name or other fields
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
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return rooms;
}



}
