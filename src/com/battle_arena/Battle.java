package com.battle_arena;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Battle {

    private List<Individuum> good_Fighter = new ArrayList<Individuum>();
    private List<Individuum> bad_fighter = new ArrayList<Individuum>();
    //private List<Weapon> wappen = new ArrayList<Weapon>();
    private Weapon weapon_1 = new Weapon(6,1,"Sword", 3,20);

    /**
     * Makes a battle and let's the battle run. happening just inside of the constructor. Which is bad.
     * @param goodies   List of individuals that mark the good side.
     * @param badies    List of individuals that mark the bad side.
     */
    public Battle(List<Individuum> goodies, List<Individuum> badies) {
        this.good_Fighter = goodies;
        this.bad_fighter = badies;
        List<Individuum> list = new ArrayList<Individuum>();
        for (Individuum g : good_Fighter) {
            list.add(g);
        }
        for (Individuum b : bad_fighter) {
            list.add(b);
        }
        List<Individuum> list1 = Combat.initiativeList(list);
        //List<Integer> indices = new ArrayList<Integer>();
        for (Individuum indi : list1) {
            System.out.println(indi.getName() + " has: " + indi.getInitiative() + " initiative and " + indi.getHealth()+"HP");

        }
        var rand = new Random();
        boolean heated_fight = true;
        while (heated_fight) {
            for (int i = 0; i < list1.size(); i++) {
                if (bad_fighter.contains(list1.get(i))) {
                    var livingEnemies = aliveEnemies(good_Fighter);
                    var attacker = list1.get(i);
                    if(livingEnemies.size() != 0) {
                        var chosen_Enemy = livingEnemies.get(rand.nextInt(livingEnemies.size()));

                        if (!attacker.getDead()) Combat.attackAction(attacker, chosen_Enemy);
                    }
                }
                if (good_Fighter.contains(list1.get(i))) {
                    var livingEnemies = aliveEnemies(bad_fighter);

                    var attacker = list1.get(i);
                    if(livingEnemies.size() != 0) {
                        var chosen_Enemy = livingEnemies.get(rand.nextInt(livingEnemies.size()));
                        if (!attacker.getDead() && !chosen_Enemy.getDead()) Combat.attackAction(attacker, chosen_Enemy);
                    }
                    //System.out.println("ITS A GOOD GUY");
                }
            }

            if (Combat.checkAlldead(good_Fighter)) {
                heated_fight = false;
                System.out.println("The good guys lost!");
                for (Individuum indi : bad_fighter) {
                    System.out.println(indi.getName() + " with "+ indi.getHealth()+ "HP");
                }
            }
            if (Combat.checkAlldead(bad_fighter)) {
                heated_fight = false;
                System.out.println("The bad guys lost!");
                for (Individuum indi : good_Fighter) {
                    System.out.println(indi.getName() + " with "+ indi.getHealth()+ "HP");
                }
            }
        }

    }

    private List<Individuum> aliveEnemies (List < Individuum > enemies) {
        var livingEnemies = new ArrayList<Individuum>();
        for (Individuum indis : enemies) {
            if (!indis.getDead()) {
                livingEnemies.add(indis);
            }
        }
        return livingEnemies;
    }
}
