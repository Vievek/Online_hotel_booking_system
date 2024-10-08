package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Favourite;



public class Favourite_util {

	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	
	public static List<Favourite> getFavouritesByUserId(int id){
	
		int ID =id;
	ArrayList<Favourite> FavouritRooms = new ArrayList<>();
	
	try {			
		con = DBconnect.getConnection()		;
		stmt = con.createStatement();
		String sql = "select f.f_id, r.r_id, r.r_type, r.descriptions,r.no_of_persons,r.price,r.availability_status,r.img1,r.img2,r.img3,r.img4,r.ac_type\r\n" + 
				"from favourite_rooms f inner join\r\n" + 
				"rooms r on f.r_id=r.r_id inner join\r\n" + 
				"registered_user u on f.ru_id=u.ru_id\r\n" + 
				"where u.ru_id = '"+ID+"';" ;
		rs=stmt.executeQuery(sql);
		
		
		while(rs.next()) {
			Favourite room = new Favourite(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12)
            );
			FavouritRooms.add(room);
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
	return FavouritRooms;
	}
}
