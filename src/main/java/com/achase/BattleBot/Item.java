package com.achase.BattleBot;

public class Item {
	private String mName;
	private float mDamage;
	
	Item(String name, float damage){
		mName = name;
		mDamage = damage;
	}
	
	public void setName(String newName) {
		this.mName = newName;
	}
	
	public String getName() {
		return this.mName;
	}
	
	public void setDamage(float newDamage) {
		this.mDamage = newDamage;
	}
	
	public float getDamage() {
		return this.mDamage;
	}
}
