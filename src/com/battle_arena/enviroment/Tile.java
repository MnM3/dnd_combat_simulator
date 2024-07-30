package com.battle_arena.enviroment;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private ArrayList<Object> occupants = new ArrayList<Object>();
    
    Tile() {
    	occupants = new ArrayList<Object>();
    }
    
    public Boolean has_occupants() {
    	if (occupants.size() > 0) {
    		return true;
    	}
    	return false;
    }
    
    public void add_occupant(Object o) {
    	occupants.add(o);
    }
    
    public void remove_occupant(Object o) {
    	occupants.remove(o);
    }

}
