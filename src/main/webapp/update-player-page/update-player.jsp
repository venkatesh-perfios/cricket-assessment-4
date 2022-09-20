<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ 
	page import="com.team.utils.DatabaseUtils, com.team.updateplayer.UpdatePlayerDao, java.sql.ResultSet"
%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Update Player</title>
	</head>
	<body>
		<%
			String name = request.getParameter("name");
			request.getSession().setAttribute("name", name);
			ResultSet resultSet = UpdatePlayerDao.getPlayerByName(name);
			if (!resultSet.next()) {
				String msg = "Player with the name " + name + " is not found!";
				out.println(msg);
			} else {
		%>
		<h1>Existing Player Details</h1>
		<form action="update-player" method="post">
			Enter name of player: <input type="text" name="name" value="<%= resultSet.getString(1) %>" required>
			<br>
			<br>
			Enter number of matches played by player: <input type="text" name="matches" value="<%= resultSet.getString(2) %>" required>
			<br>
			<br>
			Enter number of runs scored by player: <input type="number" step="1" pattern="\d+" name="runs" value="<%= resultSet.getString(3) %>" required>
			<br>
			<br>
			Enter number of wickets taken by player: <input type="number" step="1" pattern="\d+" name="wickets" value="<%= resultSet.getString(4) %>" required>
			<br>
			<br>
			Enter number of times the player was out for zero runs: <input type="number" step="1" pattern="\d+" name="zeros" value="<%= resultSet.getString(6) %>" required>
			<br>
			<br>
			<input type="submit" value="Submit!">
		</form>
		<%
			}
		%>
	</body>
</html>