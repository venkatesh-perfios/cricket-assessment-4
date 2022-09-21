<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.team.addplayer.AddPlayerDao" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Add Player</title>
		<style>
			body {
				text-align: center;
				margin: 10%;
			}
			
			a:link, a:visited {
  				text-decoration: none;
  				color: blue;
  				font-family: sans-serif;
  				font-size: 18px; 
			}
		</style>
	</head>
	<body>
		<%
			int numberOfBowlers = AddPlayerDao.getNumberOfPlayersByType("Bowler");
			int numberOfWicketKeepers = AddPlayerDao.getNumberOfPlayersByType("Wicket Keeper");
			Boolean lessMargin = (Boolean) request.getSession().getAttribute("lessMargin");
			if (lessMargin == null) {
				lessMargin = false;
			}
			
			if (AddPlayerDao.getNumberOfPlayers() == 20) {
				if (lessMargin) {
					request.getSession().setAttribute("lessMargin", false);
		%>
		<h1 style="color: red; margin-top: 10%">Cannot add more than 20 players in the squad!</h1>
		<a href='/cricket/display-all'>View all players</a>
		<%
				} else {
		%>
		<h1 style="color: red; margin-top: 25%">Cannot add more than 20 players in the squad!</h1>
		<br>
		<a href='/cricket/display-all'>View all players</a>
		<br>
		<% 
				}
			} else if (numberOfBowlers < 4) {
		%>
		<h1>You must choose at least 4 bowlers in the 20-player squad</h1>
		<h2>So far, you have chosen <%= numberOfBowlers %> bowler(s)</h2>
		<br>
		<br>
		<a href='/cricket/display-all'>View all players</a>
		<br>
		<br>
		<br>
		<form action="add-player" method="post">
			Enter name of bowler: <input type="text" name="name" required>
			<br>
			<br>
			Enter number of matches played by bowler: <input type="number" step="1" pattern="\d+" name="matches" required>
			<br>
			<br>
			Enter number of runs scored by bowler: <input type="number" step="1" pattern="\d+"name="runs" required>
			<br>
			<br>
			Enter number of wickets taken by bowler: <input type="number" step="1" pattern="\d+" name="wickets" required>
			<br>
			<br>
			Enter number of times the bowler was out for zero runs: <input type="number" step="1" pattern="\d+" name="zeros" required>
			<br>
			<br>
			<input type="radio" id="batsman" name="type" value="Batsman" disabled>
			<label for="batsman">Batsman</label>
			<input type="radio" id="all-rounder" name="type" value="Allrounder" disabled>
			<label for="all-rounder">Allrounder</label>
			<input type="radio" id="wicket-keeper" name="type" value="Wicket Keeper" disabled>
			<label for="wicket-keeper">Wicket Keeper</label>
			<input type="radio" id="bowler" name="type" value="Bowler" checked>
			<label for="bowler">Bowler</label>
			<br>
			<br>
			<input type="submit" value="Submit!">
		</form>
		<%
			} else if (numberOfWicketKeepers == 0) {
		%>
		<h1>You must choose 1 wicket keeper in the 20-player squad</h1>
		<br>
		<br>
		<a href='/cricket/display-all'>View all players</a>
		<br>
		<br>
		<br>
		<form action="add-player" method="post">
			Enter name of wicket keeper: <input type="text" name="name" required>
			<br>
			<br>
			Enter number of matches played by wicket keeper: <input type="number" step="1" pattern="\d+" name="matches" required>
			<br>
			<br>
			Enter number of runs scored by wicket keeper: <input type="number" step="1" pattern="\d+" name="runs" required>
			<br>
			<br>
			Enter number of wickets taken by wicket keeper: <input type="number" step="1" pattern="\d+" name="wickets" required>
			<br>
			<br>
			Enter number of times the wicket keeper was out for zero runs: <input type="number" step="1" pattern="\d+" name="zeros" required>
			<br>
			<br>
			<input type="radio" id="batsman" name="type" value="Batsman" disabled>
			<label for="batsman">Batsman</label>
			<input type="radio" id="all-rounder" name="type" value="Allrounder" disabled>
			<label for="all-rounder">Allrounder</label>
			<input type="radio" id="wicket-keeper" name="type" value="Wicket Keeper" checked>
			<label for="wicket-keeper">Wicket Keeper</label>
			<input type="radio" id="bowler" name="type" value="Bowler" disabled>
			<label for="bowler">Bowler</label>
			<br>
			<br>
			<input type="submit" value="Submit!">
		</form>
		<%
			} else {
		%>
		<h1>Choose a player to add in the 20-player squad</h1>
		<br>
		<br>
		<a href='/cricket/display-all'>View all players</a>
		<br>
		<br>
		<br>
		<form action="add-player" method="post">
			Enter name of player: <input type="text" name="name" required>
			<br>
			<br>
			Enter number of matches played by player: <input type="number" step="1" pattern="\d+" name="matches" required>
			<br>
			<br>
			Enter number of runs scored by player: <input type="number" step="1" pattern="\d+" name="runs" required>
			<br>
			<br>
			Enter number of wickets taken by player: <input type="number" step="1" pattern="\d+" name="wickets" required>
			<br>
			<br>
			Enter number of times the player was out for zero runs: <input type="number" step="1" pattern="\d+" name="zeros" required>
			<br>
			<br>
			<input type="radio" id="batsman" name="type" value="Batsman" required>
			<label for="batsman">Batsman</label>
			<input type="radio" id="all-rounder" name="type" value="Allrounder" required>
			<label for="all-rounder">Allrounder</label>
			<input type="radio" id="wicket-keeper" name="type" value="Wicket Keeper" required>
			<label for="wicket-keeper">Wicket Keeper</label>
			<input type="radio" id="bowler" name="type" value="Bowler" required>
			<label for="bowler">Bowler</label>
			<br>
			<br>
			<input type="submit" value="Submit!">
		</form>
		<%
			}
		%>
	</body>
</html>