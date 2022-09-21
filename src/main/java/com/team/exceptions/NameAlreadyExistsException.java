package com.team.exceptions;

public class NameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public NameAlreadyExistsException(String msg) {
		super(msg);
	}
}
