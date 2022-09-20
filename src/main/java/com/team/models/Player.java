package com.team.models;

public class Player {
	private String name;
	private int matches;
	private int runs;
	private double average;
	private int wickets;
	private int zeros;
	private String type;
	
	public Player(String name, int matches, int runs, double average, int wickets, int zeros, String type) {
		this.name = name;
		this.matches = matches;
		this.runs = runs;
		this.average = average;
		this.wickets = wickets;
		this.zeros = zeros;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getZeros() {
		return zeros;
	}

	public void setZeros(int zeroes) {
		this.zeros = zeroes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
