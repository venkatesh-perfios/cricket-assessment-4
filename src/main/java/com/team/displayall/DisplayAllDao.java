package com.team.displayall;

import java.sql.ResultSet;
import java.sql.Statement;

import com.team.utils.DatabaseUtils;

public class DisplayAllDao {
	public static ResultSet getAllPlayers() throws Exception {
		String getAllPlayersSql = "select * from Players";
		Statement statement = DatabaseUtils.getConnection().createStatement();
		
		return statement.executeQuery(getAllPlayersSql);
	}
}
