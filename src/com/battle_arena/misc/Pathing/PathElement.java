package com.battle_arena.misc.Pathing;

public class PathElement {
	Position pos;
	
	int distance_to_start;
	double distance_to_goal;
	
	public PathElement(Position pos) {
		this.pos = pos;
	}
	
	public int getPos_x() {
		return this.pos.getPos_x();
	}
	
	public int getPos_y() {
		return this.pos.getPos_y();
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public int getDistance_to_start() {
		return distance_to_start;
	}

	public void setDistance_start(int distance_start) {
		this.distance_to_start = distance_start;
	}

	public double getDistance_to_goal() {
		return distance_to_goal;
	}

	public void setDistance_goal(double distance_goal) {
		this.distance_to_goal = distance_goal;
	}

	public boolean is_adjacent(PathElement pe) {

		int distance = Position.manhatten_distance(this.getPos(), pe.getPos());
		if (distance == 1) {
			return true;
		} else {
			return false;
		}
	}
	
}
