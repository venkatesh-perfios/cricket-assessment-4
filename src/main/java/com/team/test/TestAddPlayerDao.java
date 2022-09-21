package com.team.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.team.addplayer.AddPlayerDao;
import com.team.models.Player;

public class TestAddPlayerDao {
	@Test
	public void testGetNumberOfPlayers() throws Exception {
		assert AddPlayerDao.getNumberOfPlayers() >= 0;
	}
	
	@Test
	public void testGetNumberOfPlayersByType() throws Exception {
		List<String> types = new ArrayList<>();
		types.add("Batsman");
		types.add("Bowler");
		types.add("Allrounder");
		types.add("Wicket Keeper");
		
		assert AddPlayerDao.getNumberOfPlayersByType(types.get(new Random().nextInt(4))) >= 0;
	}
	
	@Test
	public void testAddPlayer() throws Exception {
		Player player = new Player("Rohit Sharma", 86, 8779, 8779/(double)86, 12, 8, "Batsman");
		
		assert AddPlayerDao.addPlayer(player) == 1;
	}
}
