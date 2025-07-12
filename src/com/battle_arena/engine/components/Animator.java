package com.battle_arena.engine.components;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.battle_arena.Individuum;
import com.battle_arena.engine.IndividuumGameObject;
import com.battle_arena.misc.Animation;
import com.battle_arena.misc.AnimationState;
import com.battle_arena.misc.CoordinateConverter;
import com.battle_arena.misc.SpriteSheetLoader;
import com.battle_arena.misc.Vec2;
import com.battle_arena.misc.Pathing.PathElement;
//Refactor sometime in the future we want sprites. 
public class Animator {
	
	private CoordinateConverter cc;
	private IndividuumGameObject individuumGo;
	private Individuum individuum;
	private Iterator<Vec2> animationIterator;
	private Map<AnimationState, Animation> animationMap;
	private AnimationState currentAnimationState;
	private long creationTime;
	private int framesPerSecond;
	
	public void resetIterator() {
		this.animationIterator = null;
	}
	
	public void setCurrentAnimationState(AnimationState as) {
		this.currentAnimationState = as;
	}
	
	//TODO: There may be problems with the Grid offset further on.
	public Animator(IndividuumGameObject individuumGo) {
		this.individuumGo = individuumGo;
		this.individuum = individuumGo.getIndividuum();
		this.cc = new CoordinateConverter(individuum.getBattlefield(), null, null);
		this.animationIterator = null;
		this.animationMap = new HashMap<AnimationState, Animation>();
		this.creationTime = System.currentTimeMillis();
		this.framesPerSecond = 12;
	} 
	
	public void addAnimation(AnimationState as,int row, int length) throws IOException {
		SpriteSheetLoader ssl = new SpriteSheetLoader();
		Animation animation = new Animation(ssl, row, length);
		animationMap.put(as, animation);
	}
	
	//Refactor: umbenennen zu createAnimationIterator?
	public void createAnimationKeypointsFromPath(List<PathElement> path) {
		List<Vec2> animation_positions = new ArrayList<Vec2>();
		
		for (PathElement pe : path) {
			animation_positions.add(cc.positionToWindowCordinates(pe.getPos()));
		}
		//System.out.println(animation_positions.size());
		this.animationIterator = animation_positions.iterator();
	}
	
	public BufferedImage getCurrentFrame() {
		Animation a = animationMap.get(currentAnimationState);
		//current frame is equal to: (time_passed/12) % length_of_animation
		int frameIndex = (int) ((System.currentTimeMillis() - creationTime)/(1000/framesPerSecond)) % a.getLength();
		return a.getFrames().get(frameIndex);
	}

	public Iterator<Vec2> getAnimationIterator() {
		return animationIterator;
	}

	public void setAnimationIterator(Iterator<Vec2> animationIterator) {
		this.animationIterator = animationIterator;
	}
	
	
}
