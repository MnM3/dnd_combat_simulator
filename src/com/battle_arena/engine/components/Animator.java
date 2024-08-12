package com.battle_arena.engine.components;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.battle_arena.Individuum;
import com.battle_arena.engine.IndividuumGameObject;
import com.battle_arena.misc.CoordinateConverter;
import com.battle_arena.misc.Vec2;
import com.battle_arena.misc.Pathing.PathElement;
//Refactor sometime in the future we want sprites. 
public class Animator {
	
	private CoordinateConverter cc;
	private IndividuumGameObject individuumGo;
	private Individuum individuum;
	private Iterator<Vec2> animationIterator;
	
	public void resetIterator() {
		this.animationIterator = null;
	}
	
	//TODO: There may be problems with the Grid offset further on.
	public Animator(IndividuumGameObject individuumGo) {
		this.individuumGo = individuumGo;
		this.individuum = individuumGo.getIndividuum();
		this.cc = new CoordinateConverter(individuum.getBattlefield(), null, null);
		this.animationIterator = null;
	} 
	
	//Refactor: umbenennen zu createAnimationIterator?
	public void createAnimationKeypointsFromPath(List<PathElement> path) {
		List<Vec2> animation_positions = new ArrayList<Vec2>();
		
		for (PathElement pe : path) {
			animation_positions.add(cc.positionToWindowCordinates(pe.getPos()));
		}
		this.animationIterator = animation_positions.iterator();
	}

	public Iterator<Vec2> getAnimationIterator() {
		return animationIterator;
	}

	public void setAnimationIterator(Iterator<Vec2> animationIterator) {
		this.animationIterator = animationIterator;
	}
	
	
}
