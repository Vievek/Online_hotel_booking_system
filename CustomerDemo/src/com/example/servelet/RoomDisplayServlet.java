package com.example.servelet;

import com.example.dao.RoomDAO;
import com.example.model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoomDisplayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> roomList = RoomDAO.getRooms();
        request.setAttribute("rooms", roomList);
        request.getRequestDispatcher("viewRooms.jsp").forward(request, response);
    }
}
