package com.cdph.smsprank.model;

public class VictimItem
{
	private String number;
	
	private VictimItem(String number)
	{
		this.number = number;
	}
	
	public static final VictimItem create(String number)
	{
		return (new VictimItem(number));
	}
	
	public String getVictimNumber()
	{
		return number;
	}
}
