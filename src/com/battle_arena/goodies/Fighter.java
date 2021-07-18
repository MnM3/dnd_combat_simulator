package com.battle_arena.goodies;

import com.battle_arena.Combat;
import com.battle_arena.Individuum;
import com.battle_arena.Weapon;

import java.util.Random;

public class Fighter extends Individuum implements Comparable {

    private static Random random = new Random();

    public Fighter(
            String name,
            int health,
            int spells,
            int ac,
            int attackBonus,
            int intModi,
            Weapon weapon,
            int strengthMod) {
        super("",0,0,0,0,0,null,0);
        this.setName(name);
        this.setHealth(health);
        this.setSpells(spells);
        this.setAc(ac);
        this.setAttackBonus(attackBonus);
        this.setInitiativeModifier(intModi);
        this.setWeapon(weapon);
        this.setStrength_modifier(strengthMod);
    }

    public static int health(int hitDie, int constitutionModifier) {
        int health = 12;
        for(int i = 1; i<= hitDie; i++) {
            health += Combat.die(8) + constitutionModifier;
        }
        return health;
    }

    @Override
    public int compareTo(Object o) {
        if(o == null) throw new NullPointerException();
        if(o.getClass() != Individuum.class) throw new NullPointerException();
        Individuum e2 = (Individuum) o;
        return this.getInitiative() - e2.getInitiative();
    }
}
