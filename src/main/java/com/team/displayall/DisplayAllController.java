package com.team.displayall;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.models.Player;
import com.team.utils.NameComparator;

@WebServlet("/display-all")
public class DisplayAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
        PrintWriter out = response.getWriter();
        
        try {
        	ResultSet resultSet = DisplayAllDao.getAllPlayers();
	        
	        if (!resultSet.next()) {
	            out.println("<h1 color='red'>No players chosen yet for the 20-player squad!</h1>");
		        out.println("<br><br><a href='/cricket/add-player-page/add-player.jsp'>Add new player</a><br><br>");
	        } else {
	        	List<Player> players = new ArrayList<>();
	        	do {
	        		players.add(new Player(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3),
	        				resultSet.getDouble(5), resultSet.getInt(4), resultSet.getInt(6), 
	        				resultSet.getString(7)));
	        	} while (resultSet.next());
	        	Collections.sort(players, new NameComparator());
	        	
	        	out.println("<style>"
    			+ "			a:link, a:visited {\n"
    			+ "  				text-decoration: none;\n"
    			+ "  				color: green;\n"
    			+ "  				font-family: sans-serif;\n"
    			+ "  				font-size: 18px; \n"
    			+ "			}"
    			+ ""
    			+ "			edit {"
    			+ "					color: blue"
    			+ "			}"		
    			+ ""
    			+ "			delete {"
    			+ "					color: red"
    			+ "			}"		
    			+ "			</style>");
	        	
		        out.println(""
		        		+ "<br>"
		        		+ "<div style='text-align: center'>"
		        		+ "<h1>All Players List</h1>"
		        		+ "</div>");
		        

		        out.println("<br><br>"
		        		+ "<div style='text-align: center'>"
		        		+ "<a href='/cricket/add-player-page/add-player.jsp'>Add new player</a>"
		        		+ "&nbsp&nbsp&nbsp&nbsp"
		        		+ "<a href='/cricket/display-final-team'>View final team</a>"
		        		+ "</div>"
		        		+ "<br><br>");

		        out.print("<table border='1' width='80%' style='border: 1px solid black; margin-left: auto; margin-right: auto;'");
		        
		        out.print("<tr>" + 
		        		"<th>Name</th>" + 
		        		"<th>Matches</th>" + 
		        		"<th>Runs</th>" + 
		        		"<th>Wickets</th>" + 
		        		"<th>Average</th>" + 
		        		"<th>Zeros</th>" + 
		        		"<th>Type</th>" + 
		        		"<th>Edit</th>" + 
		        		"<th>Delete</th></tr>");  
		      
		        for (Player player : players) {
		        	out.print("<tr>"
		        			+ "<td style='text-align: center; vertical-align: middle;'>" + player.getName() + "</td>"
	    					+ "<td style='text-align: center; vertical-align: middle;'>" + player.getMatches() + "</td>"
							+ "<td style='text-align: center; vertical-align: middle;'>" + player.getRuns() + "</td>"
							+ "<td style='text-align: center; vertical-align: middle;'>" + player.getWickets() + "</td>"
							+ "<td style='text-align: center; vertical-align: middle;'>" + player.getAverage() + "</td>"
							+ "<td style='text-align: center; vertical-align: middle;'>" + player.getZeros() + "</td>"
							+ "<td style='text-align: center; vertical-align: middle;'>" + player.getType() + "</td>"
							+ "<td style='text-align: center; vertical-align: middle;'> <a class='edit' href='/cricket/update-player-page/update-player.jsp?name=" + player.getName() + "'>Edit</a></td>"
							+ "<td style='text-align: center; vertical-align: middle;'> <a class='delete' href='/cricket/delete-player?name=" + player.getName() + "'>Delete</a></td>"
							+ "</tr>");
		        }
		        out.print("</table>");
	        }
	        
	        out.close(); 
        } catch (Exception e) {
        	out.println("<div style='text-align: center'>"
        				+ "<h1 color='red'>Unable to display all the players at the moment! Try again later.</h1>"
        				+ "</div>"
        				+ "<br><br>");
        }
	}
}
