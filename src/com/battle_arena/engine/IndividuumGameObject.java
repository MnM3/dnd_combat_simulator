package com.battle_arena.engine;

import java.awt.Color;
import java.awt.Graphics;

import com.battle_arena.Individuum;
import com.battle_arena.misc.CoordinateConverter;
import com.battle_arena.misc.Vec2;
import com.battle_arena.misc.Pathing.Position;

public class IndividuumGameObject extends GameObject {
	
	Individuum individuum;
	CoordinateConverter converter;
	//NOTE: This constructor is deprecated
	public IndividuumGameObject(int x, int y, Individuum individuum, Grid grid, ID id) {
        super(x,y,id);
        this.individuum = individuum;
        this.converter = new CoordinateConverter(grid);
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
	}
	
	public Individuum getIndividuum() {
		return this.individuum;
	}

    public void tick() {
    	Vec2 goPosition = new Vec2(this.x, this.y);
    	Vec2 pos = converter.positionToWindowCordinates(individuum.getPosition());
    	//Refactor: should be somewhere inside a function
    	if (goPosition.getX() - pos.getX() == 0){
    		this.velX = 0;
    	}else if (goPosition.getX() - pos.getX() > 0) {
    		this.velX = -5;
    	} else if (goPosition.getX() - pos.getX() < 0)  {
    		this.velX = 5;
    	}
    	
    	if (goPosition.getY() - pos.getY() == 0){
    		this.velY = 0;
    	}else if (goPosition.getY() - pos.getY() > 0) {
    		this.velY = -5;
    	} else if (goPosition.getY() - pos.getY() < 0)  {
    		this.velY = 5;
    	}
    	
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y, 32, 32);
    }
}
