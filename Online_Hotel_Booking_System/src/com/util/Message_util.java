package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Message;

public class Message_util {

    public static List<Message> getAllMessagesByChatId(int chat_id) {
        List<Message> messages = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // SQL query to retrieve all messages for the given chat_id
        String sql = "SELECT * FROM message WHERE chat_id = ?";

        try {
            // Establish the database connection
            con = DBconnect.getConnection();
            // Prepare the SQL statement
            ps = con.prepareStatement(sql);
            // Set the chat_id parameter in the query
            ps.setInt(1, chat_id);

            // Execute the query and get the result set
            rs = ps.executeQuery();

            // Iterate through the result set and create Message objects
            while (rs.next()) {
                int message_id = rs.getInt("message_id");
                int sender_id = rs.getInt("sender_id");
                String messageText = rs.getString("message");

                // Create a new Message object
                Message message = new Message(message_id, chat_id, sender_id, messageText);

                // Add the message to the list
                messages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions (e.g., log the error)
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle closing exceptions (e.g., log the error)
            }
        }

        // Return the list of messages
        return messages;
    }
    
    public static boolean insertMessage(int chat_id, int sender_id, String messageText) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean isInserted = false;

        // SQL query to insert a new message
        String sql = "INSERT INTO message (chat_id, sender_id, message) VALUES (?, ?, ?)";

        try {
            // Establish the database connection
            con = DBconnect.getConnection();
            // Prepare the SQL statement
            ps = con.prepareStatement(sql);
            // Set the parameters in the query
            ps.setInt(1, chat_id);
            ps.setInt(2, sender_id);
            ps.setString(3, messageText);

            // Execute the update
            int rowsAffected = ps.executeUpdate();
            isInserted = rowsAffected > 0; // Check if the insertion was successful

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions (e.g., log the error)
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle closing exceptions (e.g., log the error)
            }
        }

        // Return true if the message was inserted successfully, otherwise false
        return isInserted;
    }
}
