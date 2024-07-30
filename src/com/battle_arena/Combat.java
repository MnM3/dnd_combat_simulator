package com.battle_arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.battle_arena.misc.Pathing.Position;

public class Combat {
    public static boolean roleAttack(Individuum attacker, Individuum defender) {
        int atk_bonus = attacker.getAttackBonus();
        int def_ac = defender.getAc();
        boolean success = false;
        int attackRole_value = die(20)+attacker.getAttackBonus();
        if(attackRole_value>def_ac) {
            System.out.println(attacker.getName()+" succesully attacked!");
            return success = true;
        }
        System.out.println(attacker.getName()+ " missed!");
        return success;
    }
    
    public static void moveAction(Individuum attacker, Individuum defender) {
    	Position distance = Position.distanceVector(attacker.getPosition(), defender.getPosition());
    	
    }
    
    public static void moveAction(Individuum indi, Position goal_position) {
    	
    }

    public static void attackAction(Individuum attacker, Individuum defender) {
        if(roleAttack(attacker,defender)) {
            defender.setHealth(defender.getHealth()-attacker.attackDamage(attacker.getWeapon()));
            if(!defender.getDead()) System.out.println(defender.getName() + " Is still alive with " + defender.getHealth() +"HP");
            else System.out.println(defender.getName()  + " is dead!");
        }
    }

    public static int die(int dice_type) {
        Random rand = new Random();
        return rand.nextInt(dice_type) + 1;
    }

    public static List<Individuum> initiativeList(List<Individuum> list) {
        Random rand = new Random();
        //List<Individuum> list = new ArrayList<Individuum>();
        List<Integer> initiativeList = new ArrayList<Integer>();
        for (Individuum ind : list) {
            var initiative = ind.getInitiativeModifier() + rand.nextInt(20) + 1;
            initiativeList.add(initiative);
            ind.setInitiative(initiative);
        }
        initiativeList.sort(Integer::compareTo);
        //list.sort(initiativeLis);
        list.sort(Individuum::compareTo);
        return list;
    }

    public static int checkNumberOfDead(List<Individuum> list) {
        int dead = 0;
        for (Individuum ind : list) {
            if(ind.getDead()) dead++;
        }
        return dead;
    }

    public static boolean checkAlldead(List<Individuum> list) {
        boolean allDead  = true;
        for (Individuum ind : list) {
            if (!ind.getDead()) allDead = false;
        }
        return allDead;
    }
}
