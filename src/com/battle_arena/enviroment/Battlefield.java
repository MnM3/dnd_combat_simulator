package com.battle_arena.enviroment;

import com.battle_arena.Battle;
import com.battle_arena.Individuum;
import com.battle_arena.misc.Position;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Set;

public class Battlefield {
    private Tile[][] field;
    private int dim_x;
    private int dim_y;

    public Battlefield(int height, int width) {
        this.field = new Tile[height][width];
        dim_x = height;
        dim_y = width;
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
    	Position pos = indi.getPosition();
    	int x = pos.getPos_x();
    	int y = pos.getPos_y();
    	field[x][y].remove_occpuant(indi);
    }
    
    public void move_to(Individuum indi) {
    	Position pos = indi.getPosition();
    	int x = pos.getPos_x();
    	int y = pos.getPos_y();
    	field[x][y].remove_occpuant(indi);
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
