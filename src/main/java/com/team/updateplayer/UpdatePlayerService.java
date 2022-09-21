package com.team.updateplayer;

import com.team.exceptions.MatchesInvalidException;
import com.team.exceptions.NameAlreadyExistsException;
import com.team.exceptions.RunsInvalidException;
import com.team.exceptions.WicketsInvalidException;
import com.team.exceptions.ZerosInvalidException;
import com.team.models.Player;

public class UpdatePlayerService {
	public void updatePlayer(String name, Player player) 
			throws NameAlreadyExistsException, MatchesInvalidException, RunsInvalidException, WicketsInvalidException, 
			ZerosInvalidException, Exception {
		if (UpdatePlayerDao.getPlayerByName(player.getName()).next() && !name.equals(player.getName())) {
			String msg = "The updated name already exists!";
			throw new NameAlreadyExistsException(msg);
		}
		
		if (isMatchesInvalid(player.getMatches())) {
			String msg = "Please enter a valid number of matches!";
			throw new MatchesInvalidException(msg);
		}

		if (isRunsInvalid(player.getRuns(), player.getMatches())) {
			String msg = "Please enter a valid number of runs for the given matches!";
			throw new RunsInvalidException(msg);
		}
		
		if (isWicketsInvalid(player.getWickets(), player.getMatches())) {
			String msg = "Please enter a valid number of wickets for the given matches!";
			throw new WicketsInvalidException(msg);
		}

		if (isZerosInvalid(player.getZeros(), player.getMatches(), player.getRuns())) {
			String msg = "Please enter a valid number of matches where zero was scored for the given matches and runs!";
			throw new ZerosInvalidException(msg);
		}
		
		if (UpdatePlayerDao.updatePlayerByName(name, player) != 1) {
			throw new Exception();
		}
	}
	
	private boolean isMatchesInvalid(int matches) {
		return matches < 0;
	}
	
	private boolean isRunsInvalid(int runs, int matches) {
		return runs < 0 || 
				(matches == 0 && runs > 0);
	}
	
	private boolean isWicketsInvalid(int wickets, int matches) {
		return wickets < 0 ||
				(matches == 0 && wickets > 0) ||
				(wickets > matches*10);
	}
	
	private boolean isZerosInvalid(int zeros, int matches, int runs) {
		return zeros < 0 ||
				(zeros > matches) ||
				(zeros == matches && runs > 0);
	}
}
