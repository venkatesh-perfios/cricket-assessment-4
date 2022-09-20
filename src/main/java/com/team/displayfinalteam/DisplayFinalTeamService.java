package com.team.displayfinalteam;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.team.exceptions.NotEnoughPlayersException;
import com.team.models.Player;
import com.team.utils.AverageComparator;
import com.team.utils.NameComparator;

public class DisplayFinalTeamService {
	public List<Player> getFinalTeam() throws NotEnoughPlayersException, Exception {
		if (DisplayFinalTeamDao.getNumberOfPlayers() != 20) {
			String msg = "Not enough players in the squad to form the final 11 team!";
			throw new NotEnoughPlayersException(msg);
		}
		
		ResultSet resultSet = DisplayFinalTeamDao.getAllPlayers();
		List<Player> players = new ArrayList<>();
		while (resultSet.next()) {
    		players.add(new Player(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3),
    				resultSet.getDouble(5), resultSet.getInt(4), resultSet.getInt(6), 
    				resultSet.getString(7)));
		}
		
		Collections.sort(players, new AverageComparator());
		
		List<Player> duplicatePlayers = new ArrayList<>();
		for (Player player : players) {
			duplicatePlayers.add(player);
		}
		
		List<Player> finalPlayers = new ArrayList<>();
		duplicatePlayers.stream().filter(player -> player.getType().equals("Bowler")).forEach(bowler -> {
			if (finalPlayers.size() < 3) {
				finalPlayers.add(bowler);
				players.remove(bowler);
			}
		});
		
		for (Player player : players) {
			if (finalPlayers.size() == 11)
				break;
			
			finalPlayers.add(player);
		}
		
		Collections.sort(finalPlayers, new NameComparator());
		
		return finalPlayers;
	}
}
