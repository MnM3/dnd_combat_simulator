package com.battle_arena.engine;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    RoundHandler roundHandler;

    public void tick() {
        for(int i=0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            tempObject.render(g);
        }
    }
    
    public void addRoundhandler(GameObject object) {
    	this.roundHandler = (RoundHandler) object;
    	this.objects.add(object);
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove();
    }
    
    public RoundHandler getRoundHandler() {
    	return roundHandler;
    }

}
