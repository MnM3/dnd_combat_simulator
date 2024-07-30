package com.battle_arena.misc;

import com.battle_arena.engine.Grid;
import com.battle_arena.engine.ID;
import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.misc.Pathing.Position;

public class CoordinateConverter {
	private int x_origin;
	private int y_origin;
	private int x_dim;
	private int y_dim;
	
	private Grid grid; 
	private Battlefield battlefield;
	
	public CoordinateConverter(Grid grid, Battlefield battlefield) {
		this.x_origin = grid.getX();
		this.y_origin = grid.getY();
		
		this.x_dim = grid.getDim_x();
		this.y_dim = grid.getDim_y();
		
		this.grid = grid;
		
		this.battlefield = battlefield;
	}
	
	public CoordinateConverter(Grid grid) {
		this.x_origin = grid.getX();
		this.y_origin = grid.getY();
		
		this.x_dim = grid.getDim_x();
		this.y_dim = grid.getDim_y();
		
		this.grid = grid;
		
		this.battlefield = generateBattlefieldBasedOnGrid(grid);
	}
	
	public CoordinateConverter(Battlefield battlefield, Integer x_origin, Integer y_origin) {
		x_origin = x_origin != null ? x_origin : 10;
		y_origin = y_origin != null ? y_origin : 10;
		
		this.x_origin = (int) x_origin;
		this.y_origin = (int) y_origin;
		
		this.battlefield = battlefield;
		
		this.grid = generateGridBasedOnBattlefield(battlefield, x_origin, y_origin);
	}
	
	public Battlefield generateBattlefieldBasedOnGrid(Grid grid) {
		int x_dim = grid.getDim_x();
		int y_dim = grid.getDim_y();
		
		Battlefield battlefield = new Battlefield(x_dim, y_dim);
		
		return battlefield;
	}
	
	public Grid generateGridBasedOnBattlefield(Battlefield battlefield, Integer x_origin, Integer y_origin) {
		int x_dim = battlefield.getDim_x();
		int y_dim = battlefield.getDim_y();
		
		x_origin = x_origin != null ? x_origin : 10;
		y_origin = y_origin != null ? y_origin : 10;
		
		
		Grid grid = new Grid((int) x_origin, (int) y_origin, x_dim, y_dim, ID.Grid);
		
		return grid;
	}
	
	public Vec2 positionToWindowCordinates(Position pos) {
		int wX;
		int wY;
		
		wX = pos.getPos_x() * 32 + x_origin;
		wY = pos.getPos_y() * 32 + y_origin;
		
		return new Vec2(wX, wY);
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Battlefield getBattlefield() {
		return battlefield;
	}

	public void setBattlefield(Battlefield battlefield) {
		this.battlefield = battlefield;
	}
	
	
}
