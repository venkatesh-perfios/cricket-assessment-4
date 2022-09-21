package com.team.addplayer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.exceptions.MatchesInvalidException;
import com.team.exceptions.NameAlreadyExistsException;
import com.team.exceptions.RunsInvalidException;
import com.team.exceptions.WicketKeepersLimitReachedException;
import com.team.exceptions.WicketsInvalidException;
import com.team.exceptions.ZerosInvalidException;
import com.team.models.Player;

@WebServlet("/add-player-page/add-player")
public class AddPlayerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name").toLowerCase();
		int matches = Integer.parseInt(request.getParameter("matches"));
		int runs = Integer.parseInt(request.getParameter("runs"));
		int wickets = Integer.parseInt(request.getParameter("wickets"));
		int zeros = Integer.parseInt(request.getParameter("zeros"));
		String type = request.getParameter("type");
		Player player = new Player(name, matches, runs, runs/(double)matches, wickets, zeros, type);
		
		try {
			AddPlayerService addPlayerService = new AddPlayerService();
			addPlayerService.addPlayer(player);
			out.println(""
					+ "<div style='text-align: center'>"
					+ "<font color='green'> " + name + " has been added into the 20-player squad successfully! </font>"
					+ "</div>"
					+ "<br><br>");

			request.getSession().setAttribute("lessMargin", true);
			
			RequestDispatcher rd = request.getRequestDispatcher("add-player.jsp");
			rd.include(request, response);
		} catch(NameAlreadyExistsException | MatchesInvalidException | RunsInvalidException | 
				WicketsInvalidException | ZerosInvalidException | WicketKeepersLimitReachedException exception) {
			out.println(""
					+ "<div style='text-align: center'>"
					+ "<font color='red'> " + exception.getMessage() + " </font>"
					+ "</div>"
					+ "<br><br>");

			request.getSession().setAttribute("lessMargin", false);
			
			RequestDispatcher rd = request.getRequestDispatcher("add-player.jsp");
			rd.include(request, response);
		} catch(Exception e) {
			out.println(""
					+ "<div style='text-align: center'>"
					+ "<font color='red'> Unable to add " + name + " in the 20-player squad at the moment! Try again later. </font>"
					+ "</div>"
					+ "<br><br>");

			request.getSession().setAttribute("lessMargin", false);
			
			RequestDispatcher rd = request.getRequestDispatcher("add-player.jsp");
			rd.include(request, response);
		}
	}
}
