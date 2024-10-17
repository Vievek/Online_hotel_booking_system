package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Services;
import com.model.rooms;

public class Services_util {
	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	
	public static List<Services> getAllServices(){
	
	ArrayList<Services> services = new ArrayList<>();
	
	try {			
		con = DBconnect.getConnection()		;
		stmt = con.createStatement();
		String sql = "select * from services" ;
		rs=stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			Services service = new Services(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
           
            );
			services.add(service);
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
	
	return services;
	}
	
	public static boolean insertService(String name, String description, double price, int m_id) {
	    boolean isInserted = false;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = DBconnect.getConnection();
	        String sql = "INSERT INTO services (name, description, price, m_id) VALUES (?, ?, ?, ?)";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, description);
	        pstmt.setDouble(3, price);
	        pstmt.setInt(4, m_id);
	        
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            isInserted = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return isInserted;
	}

	public static Services getServiceById(int services_id) {
	    Services service = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        con = DBconnect.getConnection();
	        String sql = "SELECT * FROM services WHERE services_id = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, services_id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            service = new Services(
	                rs.getInt("services_id"),
	                rs.getString("name"),
	                rs.getString("description"),
	                rs.getString("price"),
	                rs.getInt("m_id")
	            );
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
	    
        System.out.println("Service retrieved: " + service.getName()); // Debugging line


	    return service;
	}

	public static boolean updateService(int services_id, String name, String description, double price, int m_id) {
	    boolean isUpdated = false;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = DBconnect.getConnection();
	        String sql = "UPDATE services SET name = ?, description = ?, price = ?, m_id = ? WHERE services_id = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, name);
	        pstmt.setString(2, description);
	        pstmt.setDouble(3, price);
	        pstmt.setInt(4, m_id);
	        pstmt.setInt(5, services_id); // Set the services_id for the WHERE clause
	        
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            isUpdated = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return isUpdated;
	}
	
	public static boolean deleteService(int services_id) {
	    boolean isDeleted = false;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        con = DBconnect.getConnection();
	        String sql = "DELETE FROM services WHERE services_id = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, services_id); // Set the services_id for the WHERE clause
	        
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            isDeleted = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return isDeleted;
	}

	
}
