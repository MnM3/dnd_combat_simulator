package com.battle_arena.engine.components;

import com.battle_arena.engine.GameObject;

public abstract class GameComponent {
	GameObject gameObject;
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
}
