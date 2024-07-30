package com.battle_arena.misc.Pathing;

public class Position {

	public int x;
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static boolean equals(Position pos1, Position pos2) {
		if (pos1.getPos_x() == pos2.getPos_x() && pos1.getPos_y() == pos2.getPos_y()) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getPos_x() {
		return x;
	}

	public void setPos_x(int pos_x) {
		this.x = pos_x;
	}

	public int getPos_y() {
		return y;
	}

	public void setPos_y(int pos_y) {
		this.y = pos_y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move (int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	public static Position distanceVector(Position from, Position to) {
		return new Position(to.x - from.x, to.y - from.y);
	}
	
	public static double diagonal_magnitude(Position vector) {
		double a_squared = Math.pow(vector.x, 2);
		double b_squared = Math.pow(vector.y, 2);
		double c_squared = a_squared + b_squared;
		
		return Math.sqrt(c_squared);
	}

	//TODO refactor -- pre condition for this function may be confusing (it confused me) put this functionality inside the other function. no
	//					overload needed
	public static int manhatten_distance(Position vector) {
		return Math.abs(vector.getPos_x()) + Math.abs(vector.getPos_y());
	}

	public static int manhatten_distance(Position from, Position to) {
		return manhatten_distance(distanceVector(from, to));
	}
	public static double diagonal_magnitude(int x, int y) {
		double a_squared = Math.pow(x, 2);
		double b_squared = Math.pow(y, 2);
		double c_squared = a_squared + b_squared;
		
		return Math.sqrt(c_squared);
	}
	
	public static Position up(Position pos) {
		return new Position(pos.getPos_x(), pos.getPos_y() + 1);
	}
	
	public static Position right(Position pos) {
		return new Position(pos.getPos_x() + 1, pos.getPos_y());
	}
	
	public static Position down(Position pos) {
		return new Position(pos.getPos_x(), pos.getPos_y() - 1);
	}
	
	public static Position left(Position pos) {
		return new Position(pos.getPos_x() - 1, pos.getPos_y());
	}
}
