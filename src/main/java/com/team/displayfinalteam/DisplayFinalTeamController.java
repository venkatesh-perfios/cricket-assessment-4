package com.team.displayfinalteam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.exceptions.NotEnoughPlayersException;
import com.team.models.Player;
import com.team.utils.NameComparator;

@WebServlet("/display-final-team")
public class DisplayFinalTeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		try {
			DisplayFinalTeamService displayFinalTeamService = new DisplayFinalTeamService();
			List<Player> finalPlayers = displayFinalTeamService.getFinalTeam();

			Collections.sort(finalPlayers, new NameComparator());
        	
	        out.println("<h1>Players List</h1>");
	          
	        out.print("<table border='1' width='100%'");
	        
	        out.print("<tr>" + 
	        		"<th>Name</th>" + 
	        		"<th>Matches</th>" + 
	        		"<th>Runs</th>" + 
	        		"<th>Average</th>" + 
	        		"<th>Wickets</th>" + 
	        		"<th>Zeros</th>" + 
	        		"<th>Type</th></tr>");  
	      
	        for (Player player : finalPlayers) {
	        	out.print("<tr>"
	        			+ "<td>" + player.getName() + "</td>"
    					+ "<td>" + player.getMatches() + "</td>"
						+ "<td>" + player.getRuns() + "</td>"
						+ "<td>" + player.getAverage() + "</td>"
						+ "<td>" + player.getWickets() + "</td>"
						+ "<td>" + player.getZeros() + "</td>"
						+ "<td>" + player.getType() + "</td>"
						+ "</tr>");
	        }
	        out.print("</table");
		} catch (NotEnoughPlayersException notEnoughPlayersException) {
			out.println("<font color='red'> You must have all 20 players in the squad before forming the "
					+ "final team! </font>");
		} catch(Exception e) {
			out.println("<font color='red'> Unable to form the final team at the moment! Try again later.</font>");
		}
	}
}
