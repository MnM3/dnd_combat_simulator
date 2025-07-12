package com.battle_arena.engine;

import java.util.ArrayList;

public class CollisionSystem {
	
	CollisionSystem co;
	
	private ArrayList<GameObject> collidableObjects;
	
	private CollisionSystem() {
		collidableObjects = new ArrayList<GameObject>();
	}
	
	public CollisionSystem getInstance() {
		if (co == null) {
			return new CollisionSystem();
		}
		return co;
	}
	
	public void register_gameObject(GameObject e) {
		collidableObjects.add(e);
	}
	
	public ArrayList<GameObject> check_collision_Objects(int real_x, int real_y) {
		ArrayList<GameObject> collisionObjects = new ArrayList<GameObject>();
		for (GameObject go: collidableObjects) {
			if(go.collision_occured(real_x, real_y)) {
				collisionObjects.add(go);
			}
		}
		return collisionObjects;
	}
}
