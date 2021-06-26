package com.battle_arena.foe;

import com.battle_arena.Individuum;
import com.battle_arena.Weapon;

import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGoblin extends Individuum implements Comparable {

    public RandomGoblin() {

        super("Goblinoid_"+new Random().nextInt(1000),8,1,12,1,2,new Weapon(5,1,"Club",2,4));
    }

    @Override
    public int compareTo(Object o) {
        if(o == null) throw new NullPointerException();
        if(o.getClass() != Individuum.class) throw new NullPointerException();
        Individuum e2 = (Individuum) o;
        return this.getInitiative() - e2.getInitiative();
    }
}
