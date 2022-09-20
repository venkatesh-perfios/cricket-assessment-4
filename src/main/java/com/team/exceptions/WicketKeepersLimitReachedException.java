package com.team.exceptions;

public class WicketKeepersLimitReachedException extends Exception {
	private static final long serialVersionUID = 1L;

	public WicketKeepersLimitReachedException(String msg) {
		super(msg);
	}
}
