package com.achase.BattleBot;

import net.dv8tion.jda.core.entities.User;

public class Player {
	private User mPlayer;
	private float mHealth;
	private Item[] mInventory;
	

	Player(User player, float playerHealth) {
		this.mPlayer = player;
		this.mHealth = playerHealth;
	}

	public User getPlayerName() {
		return this.mPlayer;
	}

	public void setPlayerName(User newPlayerName) {
		this.mPlayer = newPlayerName;
	}

	public float getHealth() {
		return this.mHealth;
	}

	public void setHealth(float newHealth) {
		this.mHealth = newHealth;
	}
	
	public Item[] getInventory() {
		return this.mInventory;
	}
	
	public void setInventory(Item[] newInventory) {
		this.mInventory = newInventory;
	}
	
	public String retrievePlayerName() {
		return this.mPlayer.getName();
	}
}
