package com.battle_arena.misc.Pathing;

import java.util.ArrayList;
import java.util.List;
import com.battle_arena.Individuum;
import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;

/*
 * 	TODOS:
 *  - right now tiles where something is standing on get ignored
 * 
 */

public class PathSolver {
	private List<PathElement> path;
	private List<PathElement> graph;
	private Position goal;
	private Position start;
	private boolean reached;
	private Battlefield field;
	//REFACTOR: PathSolver as name of this class -- otherwise too much confusion with the -- DONE 
	public PathSolver (Individuum indi, Position goal_position) {
		this.start = indi.getPosition();
		this.goal = goal_position;
		this.reached = false;
		this.field = indi.getBattlefield();
		this.graph = new ArrayList<PathElement>();
		this.path = new ArrayList<PathElement>();
	}
	
	public void resetSolver() {
		this.reached = false;
		this.graph = new ArrayList<PathElement>();
		this.path = new ArrayList<PathElement>();
	}
	
	public List<PathElement> getPath() {
		return path;
	}

	public void setPath(List<PathElement> path) {
		this.path = path;
	}

	public void findPath(Position start, Position goal) {
		this.start = start;
		this.goal = goal;
		PathElement start_as_path_element = new PathElement(start);
		start_as_path_element.distance_to_start = 0;
		start_as_path_element.setDistance_goal(Position.manhatten_distance(Position.distanceVector(start_as_path_element.getPos(), this.goal)));
		//populate graph
		
		generate_graph(start_as_path_element, field); 
		//skip through graph and find the best fitting positions

		System.out.println("Graph:");
		for (PathElement pe : graph) {
			System.out.println(pe.getPos_x() + ", " + pe.getPos_y());
		}
		System.out.println(Position.equals(this.goal, new Position(9, 7)));
		System.out.println(this.goal.getPos_x() + " " + this.goal.getPos_y() + " " + this.goal.getPos_x() + " " + this.goal.getPos_y());
		PathElement goal_pe = null;
		for (PathElement pe : graph) {
			if (Position.equals(pe.getPos(), this.goal)) {
				System.out.println("found the goal!");
				System.out.println("This is start: " + start.getPos_x()+ ", " + start.getPos_y());
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

		if (Position.equals(current_pe.getPos(), this.start)) {
			return;
		}

		ArrayList<PathElement> adjacent_tiles = new ArrayList<PathElement>();
		int closest_distance = field.getDim_x() * field.getDim_y() + 1;
		for (PathElement pe : graph) {
			//we can assume
			if (pe.is_adjacent(temp_pathelement) && pe.distance_to_start <= temp_pathelement.distance_to_start) {
				adjacent_tiles.add(pe);
			}
		}
		if (adjacent_tiles.size() == 0) {
			return;
		}
		System.out.println("adjacent tile count: " + adjacent_tiles.size());
		System.out.println(temp_pathelement.distance_to_start);
		for (PathElement ape : adjacent_tiles) {
			System.out.println(ape.distance_to_start);
			if (ape.distance_to_start < closest_distance) {
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
		System.out.println("Position:" + pos.getPos_x() + ", " + pos.getPos_y());
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
				//REFACTOR?: exception should get thrown inside of the "verify_inside_dimension" and not outside.
				field.verify_inside_dimension(temp_x, temp_y);
				if (!(battle.getField()[temp_x][temp_y]).has_occupants() && !graph.contains(temp_pos)) {
					free_adjacent_positions.add(temp_pos);
				}
				
			} catch (OutOfBattlefieldDimensionException e) {
				System.out.println("OUT OF BATTLEFIELD");
				//return;
			}			
		}
		
		for (PathElement adj_pos : free_adjacent_positions) {
			Position to_start_distance = Position.distanceVector(this.start, adj_pos.getPos());
			Position to_goal_distance = Position.distanceVector(this.goal , adj_pos.getPos());
			//adj_pos.setDistance_start(Position.manhatten_distance(to_start_distance));
			adj_pos.setDistance_start(pos.getDistance_to_start() + 1);
			adj_pos.setDistance_goal(Position.diagonal_magnitude(to_goal_distance));
			System.out.println(this.goal.getPos_x() + ", " + this.goal.getPos_y() + "------ Goal");
			System.out.println(adj_pos.getPos_x() + ", " + adj_pos.getPos_y() + "-------adj position");
			if (adj_pos.getDistance_to_goal() == 0) {
				this.reached = true;
			}
			if (!graph.contains(adj_pos))
				graph.add(adj_pos);
		}
		System.out.println("-----------------------------------");
		//TODO delegate best tile -- Tile with the shortest distance to goal
		double max_distance = field.getDim_x() * field.getDim_y();
		PathElement delegate = null;
		for (PathElement pe : graph) {
			if (pe.getDistance_to_goal() < max_distance) {
				delegate = pe;
				max_distance = delegate.getDistance_to_goal();
			}
		}
		/**
		 * Wege den Pfad ein bisschern schöner zu machen:
		 * 		Weg 1: Wir nehmen anstatt der Manhatten distance zum Ziel die Luftlinie -- implemented
		 * 		Weg 2: Wir sammeln adjacent tiles mit der entsprechenden Nähe zum Ziel und wählen bei gleicher
		 * 			Entfernung einen Zufälligen Kandidaten als Delegate
		 */

		//TODO Testen ob lokale Minima existieren (meine Vermutung ist: ja, sie existieren) -- ich galube das ist mittlerweile behoben
		generate_graph(delegate, battle);
		/*for (PathElement adj_pos : free_adjacent_positions) {
			generate_graph(adj_pos, battle);
		}*/
	}
	
	
	
}
