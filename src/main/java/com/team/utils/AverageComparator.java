package com.team.utils;

import java.util.Comparator;

import com.team.models.Player;

public class AverageComparator implements Comparator<Player> {
	@Override
	public int compare(Player player1, Player player2) {
		return (int)(player2.getAverage() - player1.getAverage());
	}
}
