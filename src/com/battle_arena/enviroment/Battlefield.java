package com.battle_arena.enviroment;

import com.battle_arena.Battle;
import com.battle_arena.Individuum;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.misc.Pathing.Position;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Set;

public class Battlefield {
    private Tile[][] field;
    private int dim_x;
    private int dim_y;

    public Battlefield(int height, int width) {
        this.field = new Tile[height][width];
        dim_x = height - 1;
        dim_y = width - 1;
        for(int i = 0; i < field.length; i++) {
            for (int k = 0; k < field[i].length; k++) {
                field[i][k] = new Tile();
            }
        }
    }
    public static void main (String[] args) {
        Battlefield barrles = new Battlefield(10,10);
    }

    private ArrayList<Integer[]> spawnArea;

    public ArrayList<Integer[]> getSpawnArea() {
        return spawnArea;
    }

    /*public void setSpawnArea(int borderWidth) {
        this.spawnArea = new ArrayList<ArrayList<Individuum>>;
    }*/
    
    public void move_away(Individuum indi) {
        try {
            Position pos = indi.getPosition();
            int x = pos.getPos_x();
            int y = pos.getPos_y();
            field[x][y].remove_occupant(indi);
        } catch (NullPointerException e) {
            if (indi.getPosition() == null) {
                System.out.println("The Individuum has no po");
            } else {
                throw new NullPointerException("Can not invoke move away in battlefield, something that shouldnt be null is null.");
            }

        }
    }
    //TODO: Refactor the function also exists in Individuum -- The one at Individuum is deprecated
    public boolean verify_inside_dimension_boolean(int x, int y) {
        if (this == null) throw new NullPointerException("There is no Battlefield to verify dimensions");
        if (
                x < 0 ||
                x > this.getDim_x()
        ) 	return false;
        if (
                y < 0 ||
                y > this.getDim_y()
        )	return false;
        return true;
    }
    public void verify_inside_dimension(int x, int y) throws OutOfBattlefieldDimensionException {
        if (this == null) throw new NullPointerException("There is no Battlefield to be placed on");
        if (
                x < 0 || x > this.getDim_x()
        ) 	throw new OutOfBattlefieldDimensionException("The x Dimension of the position is to high or to small, place some");
        if (
                y < 0 ||
                        y > this.getDim_y()
        )	throw new OutOfBattlefieldDimensionException("The y Dimension of the position is to high or to small");
    }
    
    public void move_to(Individuum indi) {
    	Position pos = indi.getPosition();
    	int x = pos.getPos_x();
    	int y = pos.getPos_y();
    	field[x][y].add_occupant(indi);
    }
    
    public int getDim_x() {
    	return dim_x;
    }
    
    public int getDim_y() {
    	return dim_y;
    }
    
    public Tile[][] getField() {
        return field;
    }

    public Tile getTile(Position pos) {
    	return field[pos.getPos_x()][pos.getPos_y()];
    }
    
    public Tile getTile(int x, int y) {
    	return field[x][y];
    }
    
    public void setField(Tile[][] field) {
        this.field = field;
    }



}
