package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Favourite;

public class Favourite_util {

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static List<Favourite> getFavouritesByUserId(int id) {
        ArrayList<Favourite> FavouritRooms = new ArrayList<>();
        
        try {
            con = DBconnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT f.f_id, r.r_id, r.r_type, r.descriptions, r.no_of_persons, " +
                         "r.price, r.availability_status, r.img1, r.img2, r.img3, r.img4, r.ac_type " +
                         "FROM favourite_rooms f " +
                         "INNER JOIN rooms r ON f.r_id = r.r_id " +
                         "INNER JOIN registered_user u ON f.ru_id = u.ru_id " +
                         "WHERE u.ru_id = '" + id + "';";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return FavouritRooms;
    }

    public static boolean insertFavouriteRoom(int r_id, int ru_id) {
        boolean isSuccess = false;

        try {
            con = DBconnect.getConnection();
            stmt = con.createStatement();
            String sql = "INSERT INTO favourite_rooms VALUES (0, '" + r_id + "', '" + ru_id + "')";
            int rs = stmt.executeUpdate(sql);
            
            isSuccess = (rs > 0);
            System.out.println(isSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        }

        return isSuccess;
    }

    public static boolean deletefavroom(String Id) {
        boolean isSuccess = false;
        int CovertedId = Integer.parseInt(Id);

        try {
            con = DBconnect.getConnection();
            stmt = con.createStatement();
            String sql = "DELETE FROM favourite_rooms WHERE f_id = '" + CovertedId + "'";
            int rs = stmt.executeUpdate(sql);
            
            isSuccess = (rs > 0);
            System.out.println(isSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }
}
