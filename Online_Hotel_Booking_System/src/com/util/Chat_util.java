package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Chat;

public class Chat_util {
    private static Connection con = null;
    private static ResultSet rs = null;

    // Method to get all chats for a given worker ID (w_id) and fetch names of manager and worker
    public static List<Chat> getChatsByWorkerId(int w_id) {
        List<Chat> chatList = new ArrayList<>();

        try {
            // Get a connection from the database
            con = DBconnect.getConnection();

            // SQL query to select chats and join with user table to get names of manager and worker
            String sql = "SELECT c.chat_id, c.m_id, c.w_id, " +
                         "m.name AS managerName, w.name AS workerName " +
                         "FROM chat c " +
                         "JOIN user m ON c.m_id = m.id " + // Join to get manager name
                         "JOIN user w ON c.w_id = w.id " + // Join to get worker name
                         "WHERE c.w_id = ?";

            // Use PreparedStatement to prevent SQL injection
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, w_id);  // Set the w_id parameter

                // Execute the query
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Loop through all results and create Chat objects
                    while (rs.next()) {
                        // Create a new Chat object and set its properties
                        Chat chat = new Chat(
                            rs.getInt("chat_id"),
                            rs.getInt("m_id"),
                            rs.getInt("w_id"),
                            rs.getString("managerName"), // Get manager name from result set
                            rs.getString("workerName")   // Get worker name from result set
                        );

                        // Add the chat to the list
                        chatList.add(chat);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions by printing the stack trace
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return chatList; // Return the list of Chat objects
    }
    
   

        // Method to insert a chat record and return the last inserted chat_id
        public static int insertChat(int m_id, int w_id) {
            int lastInsertedChatId = -1;  // To store the last inserted chat_id
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Get a connection from the database
                con = DBconnect.getConnection();

                // SQL query to insert a new chat into the chat table
                String sql = "INSERT INTO chat (m_id, w_id) VALUES (?, ?)";

                // Prepare the statement
                pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                // Set the m_id and w_id parameters
                pstmt.setInt(1, m_id);
                pstmt.setInt(2, w_id);

                // Execute the update
                int affectedRows = pstmt.executeUpdate();

                // Check if the insertion was successful
                if (affectedRows > 0) {
                    // Get the generated chat_id (the last inserted ID)
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        lastInsertedChatId = rs.getInt(1);  // Get the first column (chat_id)
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();  // Handle any SQL exceptions
            } finally {
                // Close resources
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Return the last inserted chat_id
            return lastInsertedChatId;
        }

}
