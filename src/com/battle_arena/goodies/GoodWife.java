package com.battle_arena.goodies;

import com.battle_arena.Individuum;
import com.battle_arena.Weapon;

public class GoodWife extends Individuum {

    public GoodWife() {
        super("Daphne",18,2,14,3,3,new Weapon(8,1,"Sword", 2,20), 3);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
