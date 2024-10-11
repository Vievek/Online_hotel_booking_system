package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.ProfileUser;
import com.model.registered_user;

public class user_util {
	private static boolean isSuccess= false;
	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	
	public static boolean registerUser(String name,String email,String phone,String username,String password) {
		 String role = "User";
		 try {
		        con = DBconnect.getConnection();
		        stmt = con.createStatement();
		        
		        // Inserting into 'user' table and using PreparedStatement to prevent SQL injection
		        String userSql = "INSERT INTO user (name, email, phone, username, password, role) VALUES (?, ?, ?, ?, ?, ?)";
		        pstmt = con.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
		        pstmt.setString(1, name);
		        pstmt.setString(2, email);
		        pstmt.setString(3, phone);
		        pstmt.setString(4, username);
		        pstmt.setString(5, password);
		        pstmt.setString(6, role);
		        
		        int userResult = pstmt.executeUpdate();
		        
		        if (userResult > 0) {
		            // Retrieving the generated ID (primary key) from 'user' table
		            rs = pstmt.getGeneratedKeys();
		            if (rs.next()) {
		                int userId = rs.getInt(1); // ID of the inserted user
		                
		                // Inserting into 'registered_user' table using the userId as the foreign key
		                String registeredUserSql = "INSERT INTO registered_user (id) VALUES (?)";
		                pstmt = con.prepareStatement(registeredUserSql);
		                pstmt.setInt(1, userId);
		                int registeredUserResult = pstmt.executeUpdate();
		                
		                // Check if the row was successfully inserted into 'registered_user'
		                if (registeredUserResult > 0) {
		                    isSuccess = true;
		                } else {
		                    isSuccess = false;
		                }
		            }
		        } else {
		            isSuccess = false;
		        }
		        
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
	
       public static List<registered_user> validate (String userName, String password){
		
		ArrayList<registered_user> users = new ArrayList<>();
		
		try {
			con = DBconnect.getConnection()		;
			stmt = con.createStatement();		
			String sql = "select * from user where username ='"+userName+"' and password ='"+password+"'";
			ResultSet rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String userU = rs.getString(5);
				String passU = rs.getString(6);
				String role = rs.getString(7);


				registered_user u = new registered_user(id,name,email,phone,userU,passU,role);
			    users.add(u);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}
       public static int getRuIdByUserId(int userId)  {
    	    int ruId = -1; // Default value indicating not found

    	    try {
    	        con = DBconnect.getConnection();
    	        String sql = "SELECT ru_id FROM registered_user WHERE id = ?";
    	        pstmt = con.prepareStatement(sql);
    	        pstmt.setInt(1, userId);
    	        rs = pstmt.executeQuery();

    	        if (rs.next()) {
    	            ruId = rs.getInt("ru_id");
    	        }
    	    }catch(Exception e) {
    				e.printStackTrace();
    	    }
    	    finally {
    	        // Close resources in the reverse order of creation
    	        try {
    	            if (rs != null) rs.close();
    	            if (pstmt != null) pstmt.close();
    	            if (con != null) con.close();
    	        } catch (SQLException e) {
    	            e.printStackTrace(); // Handle potential SQL exceptions during closing
    	        }
    	    }
			return ruId;

       }
       
       public static int getWIdByUserId(int userId) {
    	    int wId = -1; // Default value indicating not found
    	

    	    try {
    	        con = DBconnect.getConnection(); // Obtain database connection
    	        String sql = "SELECT w_id FROM worker WHERE id = ?"; // SQL query
    	        pstmt = con.prepareStatement(sql); // Prepare statement
    	        pstmt.setInt(1, userId); // Set the user ID parameter
    	        rs = pstmt.executeQuery(); // Execute the query

    	        // Check if a result was returned
    	        if (rs.next()) {
    	            wId = rs.getInt("w_id"); // Retrieve w_id
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace(); // Print stack trace for debugging
    	    } finally {
    	        // Close resources in the reverse order of creation
    	        try {
    	            if (rs != null) rs.close();
    	            if (pstmt != null) pstmt.close();
    	            if (con != null) con.close();
    	        } catch (SQLException e) {
    	            e.printStackTrace(); // Handle potential SQL exceptions during closing
    	        }
    	    }
    	    return wId; // Return the retrieved w_id or -1 if not found
    	}

       public static int getMIdByUserId(int userId) {
    	    int mId = -1; // Default value indicating not found
    	  
    	    try {
    	        con = DBconnect.getConnection(); // Obtain database connection
    	        String sql = "SELECT m_id FROM manager WHERE id = ?"; // SQL query
    	        pstmt = con.prepareStatement(sql); // Prepare statement
    	        pstmt.setInt(1, userId); // Set the user ID parameter
    	        rs = pstmt.executeQuery(); // Execute the query

    	        // Check if a result was returned
    	        if (rs.next()) {
    	            mId = rs.getInt("m_id"); // Retrieve m_id
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace(); // Print stack trace for debugging
    	    } finally {
    	        // Close resources in the reverse order of creation
    	        try {
    	            if (rs != null) rs.close();
    	            if (pstmt != null) pstmt.close();
    	            if (con != null) con.close();
    	        } catch (SQLException e) {
    	            e.printStackTrace(); // Handle potential SQL exceptions during closing
    	        }
    	    }
    	    return mId; // Return the retrieved m_id or -1 if not found
    	}

       public static ProfileUser getUserDetailsByRuId(int ruId) {
    	   ProfileUser user = null; // Object to store user details
    	    
    	    try {
    	        con = DBconnect.getConnection(); // Obtain database connection
    	        String sql = "SELECT u.id, u.name, u.email, u.phone, u.username, u.password, u.role, u.profile " +
    	                     "FROM user u " +
    	                     "JOIN registered_user ru ON u.id = ru.id " +
    	                     "WHERE ru.ru_id = ?"; // SQL query to join user and registered_user

    	        pstmt = con.prepareStatement(sql); // Prepare the SQL statement
    	        pstmt.setInt(1, ruId); // Set the ru_id parameter
    	        rs = pstmt.executeQuery(); // Execute the query

    	        // Check if a result was returned
    	        if (rs.next()) {
    	            // Create a new User object and populate it with the retrieved data
    	            user = new ProfileUser();
    	            user.setId(rs.getInt("id"));
    	            user.setName(rs.getString("name"));
    	            user.setEmail(rs.getString("email"));
    	            user.setPhone(rs.getString("phone"));
    	            user.setUsername(rs.getString("username"));
    	            user.setPassword(rs.getString("password"));
    	            user.setRole(rs.getString("role"));
    	            user.setProfile(rs.getString("profile"));
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace(); // Print stack trace for debugging
    	    } finally {
    	        // Close resources in reverse order of creation
    	        try {
    	            if (rs != null) rs.close();
    	            if (pstmt != null) pstmt.close();
    	            if (con != null) con.close();
    	        } catch (SQLException e) {
    	            e.printStackTrace(); // Handle potential SQL exceptions during closing
    	        }
    	    }
    	    return user; // Return the User object or null if not found
    	}
       
       public static boolean updateUser(int userId, String name, String email, String phone, String username, String password, String profile) {
    	    boolean isSuccess = false;  // Track if the update is successful

    	    try {
    	        // Establishing a database connection
    	        con = DBconnect.getConnection();
    	        
    	        // Update query to modify the user data in the 'user' table
    	        String updateUserSql = "UPDATE user SET name = ?, email = ?, phone = ?, username = ?, password = ?, profile = ? WHERE id = ?";
    	        
    	        // Using PreparedStatement to avoid SQL injection
    	        pstmt = con.prepareStatement(updateUserSql);
    	        pstmt.setString(1, name);
    	        pstmt.setString(2, email);
    	        pstmt.setString(3, phone);
    	        pstmt.setString(4, username);
    	        pstmt.setString(5, password);
    	        pstmt.setString(6, profile);  // Updating the profile image path
    	        pstmt.setInt(7, userId);  // Update based on userId
    	        
    	        // Execute the update query
    	        int rowsAffected = pstmt.executeUpdate();
    	        
    	        // If at least one row was affected, the update was successful
    	        if (rowsAffected > 0) {
    	            isSuccess = true;
    	        } else {
    	            isSuccess = false;
    	        }
    	        
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    } finally {
    	        try {
    	            // Close resources
    	            if (pstmt != null) pstmt.close();
    	            if (con != null) con.close();
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
    	    
    	    return isSuccess;  // Return whether the update was successful
    	}
       
       public static boolean deleteUserByRuId(int ruId) {
    	    boolean isSuccess = false;  // Track if the deletion is successful
    	    Connection con = null;
    	    PreparedStatement pstmt = null;
    	    ResultSet rs = null;

    	    try {
    	        // Step 1: Establish a database connection
    	        con = DBconnect.getConnection();

    	        // Step 2: Retrieve the 'id' (user.id) from the 'registered_user' table before deletion
    	        String getUserIdSql = "SELECT id FROM registered_user WHERE ru_id = ?";
    	        pstmt = con.prepareStatement(getUserIdSql);
    	        pstmt.setInt(1, ruId);
    	        rs = pstmt.executeQuery();

    	        int userId = -1;
    	        if (rs.next()) {
    	            userId = rs.getInt("id");  // Fetch the user ID from registered_user
    	        }

    	        // If no matching user is found, return false
    	        if (userId == -1) {
    	            return isSuccess;  // No user found, return false
    	        }

    	        // Step 3: Delete from 'registered_user'
    	        String deleteRegisteredUserSql = "DELETE FROM registered_user WHERE ru_id = ?";
    	        pstmt = con.prepareStatement(deleteRegisteredUserSql);
    	        pstmt.setInt(1, ruId);
    	        int rowsAffectedRegisteredUser = pstmt.executeUpdate();

    	        // If the deletion from registered_user is successful, proceed to delete from 'user'
    	        if (rowsAffectedRegisteredUser > 0) {
    	            // Step 4: Delete the corresponding record in 'user' using the retrieved userId
    	            String deleteUserSql = "DELETE FROM user WHERE id = ?";
    	            pstmt = con.prepareStatement(deleteUserSql);
    	            pstmt.setInt(1, userId);
    	            int rowsAffectedUser = pstmt.executeUpdate();

    	            // If the user deletion was successful, mark the process as successful
    	            if (rowsAffectedUser > 0) {
    	                isSuccess = true;
    	            }
    	        }

    	    } catch (Exception e) {
    	        e.printStackTrace();  // Log any exceptions for debugging
    	    } finally {
    	        // Step 5: Close resources
    	        try {
    	            if (rs != null) rs.close();
    	            if (pstmt != null) pstmt.close();
    	            if (con != null) con.close();
    	        } catch (Exception e) {
    	            e.printStackTrace();  // Handle any resource-closing exceptions
    	        }
    	    }

    	    return isSuccess;  // Return whether the deletion was successful
    	}

}
