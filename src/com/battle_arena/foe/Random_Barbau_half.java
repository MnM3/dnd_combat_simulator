package com.battle_arena.foe;

import com.battle_arena.Combat;
import com.battle_arena.Individuum;
import com.battle_arena.Weapon;

import java.util.Random;

public class Random_Barbau_half extends Individuum implements Comparable {

    static Random random = new Random();

    public Random_Barbau_half() {

        super("Barbau"+new Random().nextInt(1000),hitDie(),1,ac(),4,1,new Weapon(4,1,"Claws",2,4),2);
    }

    private static int hitDie() {
        return 10 + Combat.die(4) + Combat.die(4) + Combat.die(4);
    }

    private static int ac() {
        return 14;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
