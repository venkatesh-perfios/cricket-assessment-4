package com.team.deleteplayer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete-player")
public class DeletePlayerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String name = (String) request.getParameter("name");
		
		try {
			DeletePlayerDao.deletePlayer(name);
        	out.println("<div style='text-align: center'>"
        			+ "<font color='green'> Player " + name + " has been removed from the 20-player squad successfully! </font>"
					+ "</div>"
					+ "<br><br>");
		} catch(Exception e) {
        	out.println("<div style='text-align: center'>"
        			+ "<font color='red'> Unable to remove player " + name + " from the 20-player squad at the moment! Try again later. </font>"
        			+ "</div>"
        			+ "<br><br>");
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("display-all");
			rd.include(request, response);
		}
	}
}
