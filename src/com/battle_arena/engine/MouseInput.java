package com.battle_arena.engine;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	Game game;
	
	public MouseInput(Game game) {
		super();
		this.game = game;
	}
	
	public void mouseClicked(MouseEvent e) {
		int button = e.getButton();
	
		if(button == MouseEvent.BUTTON1) {
    		Point p = MouseInfo.getPointerInfo().getLocation();
    		int x = (int) p.getX();
    		int y = (int) p.getY();
    		System.out.println("This is x: " + x);
    		System.out.println("This is y: " + y);
    		Point pos = game.getLocationOnScreen();
    		System.out.println("This is mousepos x: " + pos.x);
    		System.out.println("This is mousepos y: " + pos.y);
    		Point mousePosOnScreen = new Point(p.x - pos.x, p.y - pos.y);
    	}
		
		
	}
	
	
}
