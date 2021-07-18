package com.battle_arena.enviroment;

import com.battle_arena.Battle;
import com.battle_arena.Individuum;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Set;

public class Battlefield {
    int[][] field;

    Battlefield(int height, int width) {
        this.field = new int[height][width];
        for(int i = 0; i < field.length; i++) {
            for (int k = 0; k < field[i].length; k++) {
                field[i][k] = 1;
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

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }



}
