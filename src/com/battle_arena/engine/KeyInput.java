package com.battle_arena.engine;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.misc.Pathing.Position;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    
    public void mouseInputs() {
    	MouseInfo.getPointerInfo();
    }
     
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(handler.getRoundHandler() != null) {
        	
        }
        
        for(int i = 0; i<handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.Player) {
                //key events for player 1
                if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
                if(key == KeyEvent.VK_S) tempObject.setVelY(5);
                if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
                if(key == KeyEvent.VK_D) tempObject.setVelX(5);
            }
            
            
            
            if(tempObject.getId() == ID.RoundHandler) {
            	RoundHandler rh =  (RoundHandler) tempObject;
            	if(rh.isActionDispatched()) {
            		break;
            	}
            	
            	
            	
            	//IndividuumGameObject igo = (IndividuumGameObject) tempObject;
            	if(key == KeyEvent.VK_SPACE)
            		rh.setQueuedAction(Action.MOVE);
            		//rh.setQueuedAction(Action.MOVE);
            		//System.out.println(igo.getIndividuum().getPosition().y);
					try {
						//igo.getIndividuum().getPathSolver().resetSolver();
						//igo.getIndividuum().findPath(new Position(10,10));
						//igo.animator.createAnimationKeypointsFromPath(igo.getIndividuum().getPathSolver().getPath());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
        }

        //System.out.println(key);

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i<handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if(tempObject.getId() == ID.Player) {
                //key events for player 1
                if(key == KeyEvent.VK_W) tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);
            }
        }
    }

}
