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
	            out.println("<h1>No players chosen yet for the 20-player squad!</h1>");
		        out.println("<br><a href='/cricket/add-player-page/add-player.jsp'>Add new player</a><br><br>");
	        } else {
	        	List<Player> players = new ArrayList<>();
	        	do {
	        		players.add(new Player(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3),
	        				resultSet.getDouble(5), resultSet.getInt(4), resultSet.getInt(6), 
	        				resultSet.getString(7)));
	        	} while (resultSet.next());
	        	Collections.sort(players, new NameComparator());
	        	
		        out.println("<h1>Players List</h1>");  
		          
		        out.print("<table border='1' width='100%'");
		        
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
		        			+ "<td>" + player.getName() + "</td>"
	    					+ "<td>" + player.getMatches() + "</td>"
							+ "<td>" + player.getRuns() + "</td>"
							+ "<td>" + player.getWickets() + "</td>"
							+ "<td>" + player.getAverage() + "</td>"
							+ "<td>" + player.getZeros() + "</td>"
							+ "<td>" + player.getType() + "</td>"
							+ "<td> <a href='/cricket/update-player-page/update-player.jsp?name=" + player.getName() + "'>Edit</a></td>"
							+ "<td> <a href='/cricket/delete-player?name=" + player.getName() + "'>Delete</a></td>"
							+ "</tr>");
		        }
		        out.print("</table");
		        out.println("<br><a href='/cricket/add-player-page/add-player.jsp'>Add new player</a>");
		        out.println("&nbsp&nbsp<a href='/cricket/display-final-team'>View final team</a><br><br>");
	        } 
	        out.close(); 
        } catch (Exception e) {
        	out.println("<font color='red'>Unable to display all the players at the moment! Try again later.</font>");
        }
	}
}
