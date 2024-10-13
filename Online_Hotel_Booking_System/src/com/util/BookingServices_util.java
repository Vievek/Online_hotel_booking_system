package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.BookingServices;

public class BookingServices_util {

    private static Connection con = null;
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;
    private static boolean isSuccess = false;

    public static boolean insertBookingServices(String date, String start_time, String end_time, int services_id, int b_id) {
        int lastInsertedId = -1;
        int w_id = -1;

        try {
            con = DBconnect.getConnection();

            // Updated query with JOIN and additional conditions
            String selectWorkerSql = "SELECT w.w_id FROM worker w " +
                                     "LEFT JOIN worker_booking wb ON w.w_id = wb.w_id AND wb.date = ? " +
                                     "WHERE w.s_id = ? AND (wb.status IS NULL OR (wb.status != 'Booked' AND wb.status != 'On leave')) " +
                                     "LIMIT 1";

            pstmt = con.prepareStatement(selectWorkerSql);
            pstmt.setString(1, date);        // Bind the date for the worker_booking check
            pstmt.setInt(2, services_id);    // Bind the services_id to the query

            rs = pstmt.executeQuery();

            if (rs.next()) {
                w_id = rs.getInt("w_id");  // Get the worker ID

                // Insert into booking_services table
                String insertBookingSql = "INSERT INTO booking_services (w_id, date, start_time, end_time, services_id, b_id) " +
                                          "VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertBookingSql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, w_id);        // Bind the w_id to the insert query
                pstmt.setString(2, date);     // Bind date
                pstmt.setString(3, start_time);  // Bind start_time
                pstmt.setString(4, end_time);    // Bind end_time
                pstmt.setInt(5, services_id);    // Bind services_id
                pstmt.setInt(6, b_id);           // Bind b_id

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        lastInsertedId = rs.getInt(1);  // Get the generated ID
                    }

                    // Check if the worker already has a booking on the specified date
                    String checkBookingSql = "SELECT w_id FROM worker_booking WHERE w_id = ? AND date = ?";
                    pstmt = con.prepareStatement(checkBookingSql);
                    pstmt.setInt(1, w_id);
                    pstmt.setString(2, date);

                    rs = pstmt.executeQuery();

                    if (rs.next()) {
                        // If a record exists, update the status to "Booked"
                        String updateBookingSql = "UPDATE worker_booking SET status = 'Booked' WHERE w_id = ? AND date = ?";
                        pstmt = con.prepareStatement(updateBookingSql);
                        pstmt.setInt(1, w_id);
                        pstmt.setString(2, date);

                        pstmt.executeUpdate();  // Update the record
                    } else {
                        // If no record exists, insert a new row into worker_booking
                        String insertWorkerBookingSql = "INSERT INTO worker_booking (w_id, date, status) VALUES (?, ?, 'Booked')";
                        pstmt = con.prepareStatement(insertWorkerBookingSql);
                        pstmt.setInt(1, w_id);
                        pstmt.setString(2, date);

                        pstmt.executeUpdate();  // Insert the new record
                    }
                    isSuccess = true;
                }
            } else {
                System.out.println("No available worker found for the specified service.");
                isSuccess = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();  // Always close the connection to avoid resource leaks
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(isSuccess);
        return isSuccess;  // Return the last inserted booking_services ID
    }
    
    public static boolean deleteBookingServices(int b_id) {
        boolean isDeleted = false;

        try {
            con = DBconnect.getConnection();

            // First, retrieve the w_id and date for the given booking ID (b_id)
            String selectBookingSql = "SELECT w_id, date FROM booking_services WHERE b_id = ?";
            pstmt = con.prepareStatement(selectBookingSql);
            pstmt.setInt(1, b_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int w_id = rs.getInt("w_id");
                String date = rs.getString("date");

                // Check if there's a corresponding entry in the worker_booking table
                String checkWorkerBookingSql = "SELECT COUNT(*) FROM worker_booking WHERE w_id = ? AND date = ?";
                pstmt = con.prepareStatement(checkWorkerBookingSql);
                pstmt.setInt(1, w_id);
                pstmt.setString(2, date);
                rs = pstmt.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    // If an entry exists, delete it
                    String deleteWorkerBookingSql = "DELETE FROM worker_booking WHERE w_id = ? AND date = ?";
                    pstmt = con.prepareStatement(deleteWorkerBookingSql);
                    pstmt.setInt(1, w_id);
                    pstmt.setString(2, date);
                    pstmt.executeUpdate();  // Delete the worker booking record
                    System.out.println("Deleted entry from worker_booking.");
                } else {
                    System.out.println("No corresponding entry found in worker_booking for w_id: " + w_id + " on date: " + date);
                }

                // Check if there's a booking service entry to delete
                String checkBookingServiceSql = "SELECT COUNT(*) FROM booking_services WHERE b_id = ?";
                pstmt = con.prepareStatement(checkBookingServiceSql);
                pstmt.setInt(1, b_id);
                rs = pstmt.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    // If an entry exists, delete it
                    String deleteBookingSql = "DELETE FROM booking_services WHERE b_id = ?";
                    pstmt = con.prepareStatement(deleteBookingSql);
                    pstmt.setInt(1, b_id);
                    int bookingServiceRowsAffected = pstmt.executeUpdate(); // Delete the booking service record

                    if (bookingServiceRowsAffected > 0) {
                        System.out.println("Deleted booking service for b_id: " + b_id);
                    }
                } else {
                    System.out.println("No booking service found for b_id: " + b_id);
                }

                // Finally, delete the entry from the booking table
                String deleteBookingTableSql = "DELETE FROM booking WHERE b_id = ?";
                pstmt = con.prepareStatement(deleteBookingTableSql);
                pstmt.setInt(1, b_id);
                int bookingRowsAffected = pstmt.executeUpdate();

                if (bookingRowsAffected > 0) {
                    isDeleted = true; // Booking service and booking entry were successfully deleted
                    System.out.println("Deleted booking entry for b_id: " + b_id);
                } else {
                    System.out.println("No booking entry found for b_id: " + b_id);
                }

            } else {
                System.out.println("No booking service found with the specified booking ID (b_id).");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL exceptions
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close(); // Always close the connection to avoid resource leaks
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(isDeleted);
        return isDeleted; // Return whether the booking service and booking were deleted successfully
    }

    public static List<Integer> getBookedServiceIdsByBookingId(int bookingId) {
        List<Integer> serviceIds = new ArrayList<>();
        Connection con = null; // Ensure connection is defined

        try {
            con = DBconnect.getConnection();
            
            String sql = "SELECT services_id FROM booking_services WHERE b_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql); // Removed the extra parenthesis

            preparedStatement.setInt(1, bookingId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    serviceIds.add(resultSet.getInt("services_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in real applications
        } finally {
            if (con != null) {
                try {
                    con.close(); // Close the connection to avoid leaks
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return serviceIds;
    }
    public static List<BookingServices> getBookingServices(int bid) {
        List<BookingServices> services = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBconnect.getConnection();
            // Updated SQL query without worker table join
            String sql = "SELECT bs.date, bs.start_time, bs.end_time, bs.services_id " +
                         "FROM booking_services bs " +
                         "WHERE bs.b_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bid);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookingServices service = new BookingServices();
                service.setDate(rs.getString("date"));
                service.setStartTime(rs.getString("start_time"));
                service.setEndTime(rs.getString("end_time"));
                service.setServicesId(rs.getInt("services_id"));
                // Removed worker name retrieval
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // or handle error as needed
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return services;
    }

}
