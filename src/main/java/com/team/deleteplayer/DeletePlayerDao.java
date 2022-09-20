package com.team.deleteplayer;

import java.sql.Statement;

import com.team.utils.DatabaseUtils;

public class DeletePlayerDao {
	public static void deletePlayer(String name) throws Exception {
		String deletePlayerSql = "delete from Players where Name='" + name + "'";
		Statement statement = DatabaseUtils.getConnection().createStatement();
		statement.executeUpdate(deletePlayerSql);
	}
}
