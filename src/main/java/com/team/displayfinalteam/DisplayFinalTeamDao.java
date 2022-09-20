package com.team.displayfinalteam;

import java.sql.ResultSet;
import java.sql.Statement;

import com.team.utils.DatabaseUtils;

public class DisplayFinalTeamDao {
	private static String TABLE_NAME = "Players";
	
	public static int getNumberOfPlayers() throws Exception {
		String getNumberOfPlayersSql = "select count(*) from " + TABLE_NAME;
		Statement statement = DatabaseUtils.getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(getNumberOfPlayersSql);
		resultSet.next();
		
		return resultSet.getInt(1);
	}

	public static ResultSet getAllPlayers() throws Exception {
		String getAllPlayersSql = "select * from Players";
		Statement statement = DatabaseUtils.getConnection().createStatement();
		
		return statement.executeQuery(getAllPlayersSql);
	}
}
