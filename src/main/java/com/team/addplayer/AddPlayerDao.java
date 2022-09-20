package com.team.addplayer;

import java.sql.ResultSet;

import java.sql.Statement;

import com.team.models.Player;
import com.team.utils.DatabaseUtils;

public class AddPlayerDao {
	private static String TABLE_NAME = "Players";
	
	public static int getNumberOfPlayers() throws Exception {
		String getNumberOfPlayersSql = "select count(*) from " + TABLE_NAME;
		Statement statement = DatabaseUtils.getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(getNumberOfPlayersSql);
		resultSet.next();
		
		return resultSet.getInt(1);
	}
	
	public static int getNumberOfPlayersByType(String type) throws Exception {
		String getNumberOfPlayersByTypeSql = "select count(*) from " + TABLE_NAME + " where Type='" + type + "'";
		Statement statement = DatabaseUtils.getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(getNumberOfPlayersByTypeSql);
		resultSet.next();
		
		return resultSet.getInt(1);
	}
	
	public static int addPlayer(Player player) throws Exception {
		String addPlayerSql = "insert into " + TABLE_NAME + "(Name, Matches, Runs, Wickets, Average, Zeros, "
				+ "Type) values('" + player.getName() + "', " +
				player.getMatches() + ", " + player.getRuns() + ", " + player.getAverage() + ", " +
				player.getWickets() + ", " + player.getZeros() + ", '" + player.getType() + "')";
		Statement statement = DatabaseUtils.getConnection().createStatement();
	
		return statement.executeUpdate(addPlayerSql);
	}
}
