package com.battle_arena.misc;

import java.util.ArrayList;
import java.util.List;

import com.battle_arena.Individuum;
import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;

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
		this.field = indi.getBattlefield();
		this.graph = new ArrayList<PathElement>();
		this.path = new ArrayList<PathElement>();
	}
	
	public void findPath(Position start, Position goal) {
		this.start = start;
		this.goal = goal;
		PathElement start_as_path_element = new PathElement(start);
		//populate graph
		
		generate_graph(start_as_path_element, field); 
		//skip through graph and find the best fitting positions

		System.out.println("Graph:");
		for (PathElement pe : graph) {
			System.out.println(pe.getPos_x() + ", " + pe.getPos_y());
		}
		System.out.println(Position.equals(this.goal, new Position(9, 7)));
		PathElement goal_pe = null;
		for (PathElement pe : graph) {
			if (Position.equals(pe.getPos(), this.goal)) {
				//get_Path_from_graph(pe);
				System.out.println("found the goal!");
				goal_pe = pe;
			}
		}

		get_Path_from_graph(goal_pe);

		//show path
		System.out.println("Found Path:");
		for (PathElement pe : path) {
			System.out.println(pe.getPos_x() + ", " + pe.getPos_y());
		}
	}
	

	private void get_Path_from_graph(PathElement current_pe) {
		//loop over graph find the minimum in two steps?
		//start with goal -- find tile that is inside of graph with shortest distance to start that is also adjacent
		//find start for path
		PathElement temp_pathelement = current_pe;

		ArrayList<PathElement> adjacent_tiles = new ArrayList<PathElement>();
		int closest_distance = field.getDim_x() * field.getDim_y() + 1;
		for(PathElement pe : graph) {
			//we can assume
			if (pe.is_adjacent(temp_pathelement)) {
				//if it has the smallest distance
				//if (pe.distance_to_start < closest_distance) {
				adjacent_tiles.add(pe);
				//}
			}
		}
		for (PathElement ape : adjacent_tiles) {
			if(ape.distance_to_start < closest_distance) {
				temp_pathelement = ape;
				closest_distance = ape.distance_to_start;
			}
		}

		path.add(temp_pathelement);
		if (temp_pathelement.getPos().equals(start)) {
			return;
		} else {
			get_Path_from_graph(temp_pathelement);
		}


	}


	
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

			try {
				field.verify_inside_dimension(temp_x, temp_y);
				if (!(battle.getField()[temp_x][temp_y]).has_occupants() && !graph.contains(temp_pos)) {
					free_adjacent_positions.add(temp_pos);
					//System.out.println(temp_pos.getPos_x() + ", " + temp_pos.getPos_y());
				}
			} catch (OutOfBattlefieldDimensionException e) {
				return;
			}
		}
		
		for (PathElement adj_pos : free_adjacent_positions) {
			//int start_distance = pos.getDistance_start();
			//adj_pos.setDistance_start(0);
			//TODO this part is devoid of logic (this is the curlprit of all problems)
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
		//return free_adjacent_positions;
	}
	
	
	
}
