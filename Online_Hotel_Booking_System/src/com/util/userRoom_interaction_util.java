package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class userRoom_interaction_util {
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    private static boolean isSuccess = false;

    public static boolean addUserInteraction(int user_id, int rooms_id) {
        try {
            con = DBconnect.getConnection();
            stmt = con.createStatement();

            System.out.println(user_id + " " + rooms_id + " in util");

            // Check for existing interaction
            String checkSql = "SELECT * FROM user_room_interactions WHERE user_id = ? AND room_id = ?";
            pstmt = con.prepareStatement(checkSql);
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, rooms_id);
            rs = pstmt.executeQuery();

            // If an existing interaction is found, delete it
            if (rs.next()) {
                String deleteSql = "DELETE FROM user_room_interactions WHERE user_id = ? AND room_id = ?";
                pstmt = con.prepareStatement(deleteSql);
                pstmt.setInt(1, user_id);
                pstmt.setInt(2, rooms_id);
                pstmt.executeUpdate();
                System.out.println("Deleted existing interaction for user_id: " + user_id + " and room_id: " + rooms_id);
            }

            // Inserting into 'user_room_interactions' table
            String userSql = "INSERT INTO user_room_interactions (user_id, room_id) VALUES (?, ?)";
            pstmt = con.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, user_id);
            pstmt.setInt(2, rooms_id);

            int userResult = pstmt.executeUpdate();

            // Set success based on the result of the insertion
            isSuccess = userResult > 0;

            System.out.println(isSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }
}
