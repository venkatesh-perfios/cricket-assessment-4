package com.team.displayfinalteam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		try {
			DisplayFinalTeamService displayFinalTeamService = new DisplayFinalTeamService();
			List<Player> finalPlayers = displayFinalTeamService.getFinalTeam();

			Collections.sort(finalPlayers, new NameComparator());
        	
	        out.println(""
	        		+ "<br>"
	        		+ "<div style='text-align: center'>"
	        		+ "<h1>Final 11 Players List</h1>"
	        		+ "</div>"
	        		+ "<br><br>");
	          
	        out.print("<table border='1'  width='80%' style='border: 1px solid black; margin-left: auto; "
	        		+ "margin-right: auto;'");
	        
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
	        			+ "<td style='text-align: center; vertical-align: middle;'>" + player.getName() + "</td>"
    					+ "<td style='text-align: center; vertical-align: middle;'>" + player.getMatches() + "</td>"
						+ "<td style='text-align: center; vertical-align: middle;'>" + player.getRuns() + "</td>"
						+ "<td style='text-align: center; vertical-align: middle;'>" + player.getAverage() + "</td>"
						+ "<td style='text-align: center; vertical-align: middle;'>" + player.getWickets() + "</td>"
						+ "<td style='text-align: center; vertical-align: middle;'>" + player.getZeros() + "</td>"
						+ "<td style='text-align: center; vertical-align: middle;'>" + player.getType() + "</td>"
						+ "</tr>");
	        }
	        out.print("</table");
	        out.println("<br><br>"
	        		+ "<div style='text-align: center'>"
	        		+ "<a href='/cricket/display-all'>View all players</a>"
	        		+ "<br><br>");
		} catch (NotEnoughPlayersException notEnoughPlayersException) {
			out.println(""
					+ "<div style='text-align: center'>"
					+ "<font color='red'> You must have all 20 players in the squad before forming the final team! </font>"
					+ "</div>"
					+ "<br><br>");
			RequestDispatcher rd = request.getRequestDispatcher("display-all");
			rd.include(request, response);
		} catch(Exception e) {
			out.println(""
					+ "<div style='text-align: center'>"
					+ "<font color='red'> Unable to form the final team at the moment! Try again later.</font>"
					+ "</div>"
					+ "<br><br>");
			RequestDispatcher rd = request.getRequestDispatcher("display-all");
			rd.include(request, response);
		}
	}
}
