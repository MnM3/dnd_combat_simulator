package com.battle_arena.misc;

public class Vec2 {
	int x;
	int y;
	
	//NOTE: We just need Vec2 for knowing when to reset positions. etc. They dont have any other purpose,
	//		Position is what holds purpose
	
	public Vec2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
