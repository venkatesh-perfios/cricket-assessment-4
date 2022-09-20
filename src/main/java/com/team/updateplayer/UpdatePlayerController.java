package com.team.updateplayer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.exceptions.MatchesInvalidException;
import com.team.exceptions.RunsInvalidException;
import com.team.exceptions.WicketsInvalidException;
import com.team.exceptions.ZerosInvalidException;
import com.team.models.Player;

@WebServlet("/update-player-page/update-player")
public class UpdatePlayerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();

		String name = (String) request.getSession().getAttribute("name");
		String newName = request.getParameter("name");
		int matches = Integer.parseInt(request.getParameter("matches"));
		int runs = Integer.parseInt(request.getParameter("runs"));
		int wickets = Integer.parseInt(request.getParameter("wickets"));
		int zeros = Integer.parseInt(request.getParameter("zeros"));
		Player player = new Player(newName, matches, runs, runs/(double)matches, wickets, zeros, null);
		
		try {
			UpdatePlayerService updatePlayerService = new UpdatePlayerService();
			updatePlayerService.updatePlayer(name, player);
			out.println("<font color='green'>Player with the previous name " + name + " has been updated successfully!</font>");
		} catch(MatchesInvalidException | RunsInvalidException | WicketsInvalidException | 
				ZerosInvalidException exception) {
			out.println("<font color='red'> " + exception.getMessage() + " </font>");
		} catch(Exception e) {
			out.println("<font color='red'> Unable to update player with previous name " + name + " in the 20-player squad at the moment! Try again later. </font>");
		}
	}
}
