package com.team.exceptions;

public class PlayerNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public PlayerNotFoundException(String msg) {
		super(msg);
	}
}
