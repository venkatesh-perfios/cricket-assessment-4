package com.team.exceptions;

public class PlayersLimitReachedException extends Exception {
	private static final long serialVersionUID = 1L;

	public PlayersLimitReachedException(String msg) {
		super(msg);
	}
}
