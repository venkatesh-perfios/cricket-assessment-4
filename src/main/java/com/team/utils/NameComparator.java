package com.team.utils;

import java.util.Comparator;

import com.team.models.Player;

public class NameComparator implements Comparator<Player> {
	@Override
	public int compare(Player player1, Player player2) {
		return player1.getName().compareTo(player2.getName());
	}
}
