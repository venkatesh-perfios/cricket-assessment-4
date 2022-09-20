package com.team.exceptions;

public class NotEnoughPlayersException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotEnoughPlayersException(String msg) {
		super(msg);
	}
}
