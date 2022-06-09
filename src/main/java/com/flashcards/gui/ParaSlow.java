package com.flashcards.gui;

public class ParaSlow 
{
	private String slowoAngielskie;
	private String slowoPolskie;
	
	public String getSlowoAngielskie() {
		return slowoAngielskie;
	}
	public void setSlowoAngielskie(String slowoAngielskie) {
		this.slowoAngielskie = slowoAngielskie;
	}
	public String getSlowoPolskie() {
		return slowoPolskie;
	}
	public void setSlowoPolskie(String slowoPolskie) {
		this.slowoPolskie = slowoPolskie;
	}
	
	public ParaSlow(String slowoPolskie, String slowoAngielskie)
	{
		this.slowoAngielskie = slowoAngielskie;
		this.slowoPolskie = slowoPolskie;
	}
	
}
