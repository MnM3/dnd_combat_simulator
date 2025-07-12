package com.battle_arena.engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.battle_arena.Combat;
import com.battle_arena.ControlMode;
import com.battle_arena.Individuum;
import com.battle_arena.Conditions.AbstractCondition;
import com.battle_arena.misc.Pathing.Position;

public class RoundHandler extends GameObject {
	
	private static RoundHandler roundHandler;
	
	private boolean actionDispatched;
	
	private AbstractCondition condition;
	
	private boolean aiMode;
	
	private List<GameObject> gameObjects;
	private int round;
	private int turn;
	private List<Individuum> individuums;
	private List<Individuum> individuumsSortedByInitiative;
	
	public RoundHandler() {
		super(-50, -50 , ID.RoundHandler);
		this.round = 0;
		this.turn = 0;
		this.gameObjects = new ArrayList<GameObject>();
		this.individuums = new ArrayList<Individuum>();
		this.individuumsSortedByInitiative = new ArrayList<Individuum>();
	}
	
	public static RoundHandler getInstance() {
		if(roundHandler == null) {
			roundHandler = new RoundHandler();
		}
		return roundHandler;
	}
	
	private Action queuedAction;
	
	public void setQueuedAction(Action action) {
		queuedAction = action;
	}
	
	public Action getQueuedAction() {
		return queuedAction;
	}
	
	public void tick() {
		
		if (actionDispatched) {
			queuedAction = null;
			//Check is action is fullfilled
			return;
		}
		if (round == 0) {
			individuumsSortedByInitiative = Combat.initiativeList(individuums);
			round++;
			return;
		}
		
		if(turn >= individuumsSortedByInitiative.size()) {
			turn = 0;
		}
		
		if(individuumsSortedByInitiative.size() == 0) {
			return;
		}
		
		Individuum activeIndividuum;
		int indexOfActivePlayer = turn;
		//System.out.println(indexOfActivePlayer);
		activeIndividuum = individuumsSortedByInitiative.get(indexOfActivePlayer);
		if(activeIndividuum.getControlMode() == ControlMode.PLAYER) {
			//Check input Action
			if(queuedAction == null ? false : queuedAction.equals(Action.MOVE)) {
				activeIndividuum.getPathSolver().resetSolver();
				activeIndividuum.findPath(new Position(10,10));
				((IndividuumGameObject) activeIndividuum.getGameObject()).animator.createAnimationKeypointsFromPath(activeIndividuum.getPathSolver().getPath());
				
			}
			//if an action is send to the handler do something
			//	if visual mode is active dispatch
		} else {
			//let AI decide what to do
			//if visual mode is active
		}
		//make a decision, 
		//dispatch
		if (!actionDispatched) {
			turn++;
		}

		
	}
	
	public void addGameobject(IndividuumGameObject individuumGameObject) {
		this.gameObjects.add(individuumGameObject);
		this.individuums.add(individuumGameObject.getIndividuum());
	}
	
	public void addIndividuum(Individuum individuum) {
		this.individuums.add(individuum);
	}

	public boolean isActionDispatched() {
		return actionDispatched;
	}

	public void setActionDispatched(boolean actionDispatched) {
		this.actionDispatched = actionDispatched;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_Layer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean collison_occured(int real_x, int real_y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
