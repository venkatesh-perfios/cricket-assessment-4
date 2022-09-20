package com.team.addplayer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.exceptions.MatchesInvalidException;
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
		
		String name = request.getParameter("name");
		int matches = Integer.parseInt(request.getParameter("matches"));
		int runs = Integer.parseInt(request.getParameter("runs"));
		int wickets = Integer.parseInt(request.getParameter("wickets"));
		int zeros = Integer.parseInt(request.getParameter("zeros"));
		String type = request.getParameter("type");
		Player player = new Player(name, matches, runs, runs/(double)matches, wickets, zeros, type);
		
		try {
			AddPlayerService addPlayerService = new AddPlayerService();
			addPlayerService.addPlayer(player);
			out.println("<font color='green'> " + name + " has been added into the 20-player squad successfully! </font>");
		} catch(MatchesInvalidException | RunsInvalidException | WicketsInvalidException | 
				ZerosInvalidException | WicketKeepersLimitReachedException exception) {
			out.println("<font color='red'> " + exception.getMessage() + " </font>");
		} catch(Exception e) {
			out.println("<font color='red'> Unable to add " + name + " in the 20-player squad at the moment! Try again later. </font>");
		}
	}
}
