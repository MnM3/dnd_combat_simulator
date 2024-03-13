package com.battle_arena.misc;

import java.util.ArrayList;
import java.util.List;

import com.battle_arena.Individuum;
import com.battle_arena.enviroment.Battlefield;

public class Path {
	private List<PathElement> path;
	private List<PathElement> graph;
	private Position goal;
	private Position start;
	private boolean reached;
	private Battlefield field;
	public Path (Individuum indi, Position goal_position) {
		this.start = indi.getPosition();
		this.goal = goal_position;
		this.reached = false;
		
	}
	
	public void findPath(Position start, Position goal) {
		this.start = start;
		this.goal = goal;
		PathElement start_as_path_element = new PathElement(start);
		//populate graph
		
		generate_graph(start_as_path_element, field); 
		//skip through graph and find the best fitting positions
	}
	
	public 
	
	//TODO: I have a feeling, that this should be part of the battlefield class
	public void generate_graph(PathElement pos, Battlefield battle) { //
		if (this.reached == true) return;
		List<PathElement> free_adjacent_positions = new ArrayList<PathElement>();
		List<PathElement> temp_list = new ArrayList<PathElement>();
		temp_list.add(new PathElement(Position.up(pos.getPos())));
		temp_list.add(new PathElement(Position.right(pos.getPos())));
		temp_list.add(new PathElement(Position.down(pos.getPos())));
		temp_list.add(new PathElement(Position.left(pos.getPos())));
		for (PathElement temp_pos : temp_list) {
			int temp_x = temp_pos.getPos_x();
			int temp_y = temp_pos.getPos_y();
			if (!(battle.getField()[temp_x][temp_y]).has_occupants() && !graph.contains(temp_pos)) {
				free_adjacent_positions.add(temp_pos);
			}
		}
		
		for (PathElement adj_pos : free_adjacent_positions) {
			//int start_distance = pos.getDistance_start();
			//adj_pos.setDistance_start(0);
			Position to_start_distance = Position.distanceVector(this.start, adj_pos.getPos());
			Position to_goal_distance = Position.distanceVector(this.goal , adj_pos.getPos());
			adj_pos.setDistance_goal(Position.manhatten_distance(to_start_distance));
			adj_pos.setDistance_goal(Position.manhatten_distance(to_goal_distance));
			if (adj_pos.getDistance_to_goal() == 0) {
				this.reached = true;
			}
			graph.add(adj_pos);
		}
		
		for (PathElement adj_pos : free_adjacent_positions) {
			generate_graph(adj_pos, battle);
		}
		
		
		/*
		 * Zuerst müssen wir Tiles finden die begehbar sind und Sinn ergeben. Wir gucken immer alle unsere umliegenden Tiles an und fügen dass hinzu welches 
		 * am meisten Sinn ergibt. Das Tile was am meisten Sinn ergibt ist am kürzesten von dem Ursprung entfernt und 
		*/
		return free_adjacent_positions;
	}
	
	
	
}
