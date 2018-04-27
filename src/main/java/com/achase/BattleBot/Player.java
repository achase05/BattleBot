package com.achase.BattleBot;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Player {
	private static User mPlayer;
	private float mHealth;
	public List<Item> mInventory = new ArrayList<Item>();
	

	Player(User player, float playerHealth) {
		this.mPlayer = player;
		this.mHealth = playerHealth;
	}

	public User getPlayer() {
		return this.mPlayer;
	}

	public void setPlayer(User newPlayerName) {
		this.mPlayer = newPlayerName;
	}

	public float getHealth() {
		return this.mHealth;
	}

	public void setHealth(float newHealth) {
		this.mHealth = newHealth;
	}
	
//	public Item[] getInventory() {
//		return this.mInventory;
//	}
//	
//	public void setInventory(Item[] newInventory) {
//		this.mInventory = newInventory;
//	}
	
	public String retrievePlayerName() {
		return this.mPlayer.getName();
	}
	
	public void addItemToInv(Item item ) {
		this.mInventory.add(item);
	}
	
	public void openChest(MessageChannel chnl) {
		Item item = new Item("Dagger", 10);
		this.addItemToInv(item);
		chnl.sendMessage(this.mPlayer.getAsMention() + ", you opened a chest and found a " + item.getName() + "!").queue();
	}
	
	public void use(String itemName, Player tgtPlayer, MessageChannel chnl) {
		for(int i = 0; i <= this.mInventory.size(); i++) {
			if(mInventory.get(i).getName() == itemName) {
				float newHealth = tgtPlayer.getHealth() - mInventory.get(i).getDamage();
				tgtPlayer.setHealth(newHealth);
				chnl.sendMessage(this.mPlayer.getAsMention() + ", you attacked " + tgtPlayer.getPlayer().getAsMention() 
						+ "with a " + itemName + ". You dealt " + mInventory.get(i).getDamage() + ". " + tgtPlayer.getPlayer().getAsMention() 
						+ " has " + tgtPlayer.getHealth() + " remaining.");
				this.mInventory.remove(i);
			}
		}
	}
	
	
}
