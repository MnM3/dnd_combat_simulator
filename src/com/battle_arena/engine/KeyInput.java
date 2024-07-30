package com.battle_arena.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.misc.Pathing.Position;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player) {
                //key events for player 1
                if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
                if(key == KeyEvent.VK_S) tempObject.setVelY(5);
                if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
                if(key == KeyEvent.VK_D) tempObject.setVelX(5);
            }
            
            if(tempObject.getId() == ID.Individuum) {
            	IndividuumGameObject igo = (IndividuumGameObject) tempObject;
            	if(key == KeyEvent.VK_SPACE)
					try {
						igo.getIndividuum().setPosition(new Position(8,8));
					} catch (OutOfBattlefieldDimensionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
        }

        //System.out.println(key);

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

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
