package com.team.updateplayer;

import java.sql.ResultSet;
import java.sql.Statement;

import com.team.models.Player;
import com.team.utils.DatabaseUtils;

public class UpdatePlayerDao {
	public static ResultSet getPlayerByName(String name) throws Exception {
		String getPlayerByNameSql = "select * from Players where Name='" + name + "'";
		Statement statement = DatabaseUtils.getConnection().createStatement();
		
		return statement.executeQuery(getPlayerByNameSql);
	}

	public static int updatePlayerByName(String name, ResultSet resultSet, Player player) throws Exception {
		String getPlayerByNameSql = "update Players set Name='" + player.getName() + "', Matches=" + 
				player.getMatches() + ", Runs=" + player.getRuns() + ", Average=" + player.getAverage() + 
				", Wickets=" + player.getWickets() + ", Zeros=" + player.getZeros() + " where Name='" + name + "'";
		Statement statement = DatabaseUtils.getConnection().createStatement();
		
		return statement.executeUpdate(getPlayerByNameSql);
	}
}
