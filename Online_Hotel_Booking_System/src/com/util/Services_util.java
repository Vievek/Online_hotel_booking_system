package com.util;

import java.sql.Connection;
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
	
}
