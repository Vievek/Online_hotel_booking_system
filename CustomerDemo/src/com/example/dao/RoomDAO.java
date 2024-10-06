package com.example.dao;

import com.customer.DBconnect;
import com.example.model.Room;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    private static Connection con = null;
    private static Statement stmt = null;

    // Method to upload room image and save room details
    public static boolean saveRoom(String roomName, String imagePath) {
        boolean isSuccess = false;

        try {
            // Connect to the database
        	con = DBconnect.getConnection();
			stmt = con.createStatement();

            // SQL query to insert room details (including image path)
            String sql = "INSERT INTO rooms (name, imagePath) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            // Set the parameters for the query
            pstmt.setString(1, roomName);
            pstmt.setString(2, imagePath);

            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }
    
    public static List<Room> getRooms() {
        List<Room> roomList = new ArrayList<>();
        try {
            con = DBconnect.getConnection();
            String sql = "SELECT * FROM rooms";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id"); // Assuming there's an 'id' field
                String name = rs.getString("name");
                String imagePath = rs.getString("imagePath");

                Room room = new Room(id, name, imagePath);
                roomList.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roomList;
    }
}
