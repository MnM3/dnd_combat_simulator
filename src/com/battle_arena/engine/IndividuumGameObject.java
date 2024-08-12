package com.battle_arena.engine;

import java.awt.Color;
import java.awt.Graphics;

import com.battle_arena.Individuum;
import com.battle_arena.engine.components.Animator;
import com.battle_arena.misc.CoordinateConverter;
import com.battle_arena.misc.Vec2;
import com.battle_arena.misc.Pathing.Position;

public class IndividuumGameObject extends GameObject {
	
	Individuum individuum;
	CoordinateConverter converter;
	Animator animator;
	private int speed = 1;
	
	private Vec2 nextPosition;
	//NOTE: This constructor is deprecated
	public IndividuumGameObject(int x, int y, Individuum individuum, Grid grid, ID id) {
        super(x,y,id);
        this.individuum = individuum;
        this.converter = new CoordinateConverter(grid);
        this.animator = new Animator(this);
        this.nextPosition = null;
    }
	
	//NOTE: Right now this is not finished, but also already deprecated
	public IndividuumGameObject(Individuum individuum, Grid grid, ID id) {
		super(0, 0, id);
		
		this.converter = new CoordinateConverter(grid);
		
		Vec2 pos  = converter.positionToWindowCordinates(individuum.getPosition());
		
		this.x = pos.getX();
		this.y = pos.getY();
		
		this.individuum = individuum;
		
	}
	
	public IndividuumGameObject(Individuum individuum, ID id) {
		super(0,0, id);
		this.converter = new CoordinateConverter(individuum.getBattlefield(), null, null);
		
		Vec2 pos  = converter.positionToWindowCordinates(individuum.getPosition());
		
		this.x = pos.getX();
		this.y = pos.getY();
		
		this.individuum = individuum;
		this.animator = new Animator(this);
	}
	
	private void velocitySetter() {
		Vec2 goPosition = new Vec2(this.x, this.y);
		
		//init
		if(nextPosition == null && animator.getAnimationIterator() != null && animator.getAnimationIterator().hasNext()) {
			nextPosition = animator.getAnimationIterator().next();
		} else if (nextPosition != null) { //already initialized
			if (goPosition.getX() - nextPosition.getX() == 0 && goPosition.getX() - nextPosition.getY() == 0) {
				if(animator.getAnimationIterator().hasNext()) {
					nextPosition = animator.getAnimationIterator().next();
				} else {
					nextPosition = null;
					this.velX = 0;
					this.velY = 0;
				}
			} else {
				if (goPosition.getX() - nextPosition.getX() == 0){
		    		this.velX = 0;
		    	}else if (goPosition.getX() - nextPosition.getX() > 0) {
		    		this.velX = -speed;
		    	} else if (goPosition.getX() - nextPosition.getX() < 0)  {
		    		this.velX = speed;
		    	}
				
				if (goPosition.getY() - nextPosition.getY() == 0){
		    		this.velY = 0;
		    	}else if (goPosition.getY() - nextPosition.getY() > 0) {
		    		this.velY = -speed;
		    	} else if (goPosition.getY() - nextPosition.getY() < 0)  {
		    		this.velY = speed;
		    	}
			}
		}
		
		
	}
	
    public void tick() {
    	velocitySetter();
    	
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y, 32, 32);
    }

	public void setIndividuum(Individuum individuum) {
		this.individuum = individuum;
	}
	
	public Individuum getIndividuum() {
		return this.individuum;
	}

	
    
}
