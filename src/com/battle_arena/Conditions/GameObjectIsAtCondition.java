package com.battle_arena.Conditions;

import com.battle_arena.engine.GameObject;

public class GameObjectIsAtCondition extends AbstractCondition {
	GameObject gameObject;
	int goal_x;
	int goal_y;
	
	public GameObjectIsAtCondition(GameObject go, int x, int y) {
		this.gameObject = go;
		this.goal_x = x;
		this.goal_y = y;
	}
	
	@Override
	boolean check() {
		if((gameObject.getX() == goal_x) && (gameObject.getY() == goal_y))
			return true;
		else
			return false;
	}
	
}
