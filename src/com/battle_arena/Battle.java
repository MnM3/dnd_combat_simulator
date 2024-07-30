package com.battle_arena;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.misc.Pathing.Position;

public class Battle {

    private List<Individuum> good_Fighter = new ArrayList<Individuum>();
    private List<Individuum> bad_fighter = new ArrayList<Individuum>();
    //private List<Weapon> wappen = new ArrayList<Weapon>();
    private Weapon weapon_1 = new Weapon(6,1,"Sword", 3,20);
    private boolean heated_fight;
    private int round_counter;
    private List<Individuum> initiative_list;
    private Battlefield battlefield;

    /**
     * Makes a battle and let's the battle run. happening just inside of the constructor. Which is bad.
     * @param goodies   List of individuals that mark the good side.
     * @param badies    List of individuals that mark the bad side.
     * @throws OutOfBattlefieldDimensionException 
     */
    public Battle(List<Individuum> goodies, List<Individuum> badies) throws OutOfBattlefieldDimensionException {
        this.good_Fighter = goodies;
        this.bad_fighter = badies;
        List<Individuum> list = new ArrayList<Individuum>();
        for (Individuum g : good_Fighter) {
            list.add(g);
        }
        for (Individuum b : bad_fighter) {
            list.add(b);
        }
        initiative_list = Combat.initiativeList(list);
        //List<Integer> indices = new ArrayList<Integer>();
        for (Individuum indi : initiative_list) {
            System.out.println(indi.getName() + " has: " + indi.getInitiative() + " initiative and " + indi.getHealth()+"HP");
        }
        round_counter = 1;
        heated_fight = true;
        
        battlefield = new Battlefield(10, 10);
        
        for (Individuum g: good_Fighter) {
        	g.setBattlefield(battlefield);
        	
        }
        
        for (Individuum b : bad_fighter) {
        	b.setBattlefield(battlefield);
        }
        
        int counter = 3;
        for (Individuum g: good_Fighter) {
        	g.setPosition(new Position(counter, 1));
        	counter++;
        }
        
        counter = 3;
        for (Individuum b: bad_fighter) {
        	b.setPosition(new Position (counter, 9));
        }
        
    }
    
    
    
    private void place_individuum(Individuum indi) {
    	Position indiPosition = indi.getPosition();
    	int x = indiPosition.getPos_x();
    	int y = indiPosition.getPos_y();
    	this.battlefield.getField()[x][y].add_occupant(indi);;
    }
    
    private void move_individuum() {
    	
    }
    
    public void fight_till_death() {
    	while(heated_fight) {
    		fight_one_round();
    	}
    }
    
    public void fight_one_round() {
    	var rand = new Random();
    	if (heated_fight) {
        	System.out.println("========= Start of Round: " + round_counter + " =========");
            for (int i = 0; i < initiative_list.size(); i++) {
                if (bad_fighter.contains(initiative_list.get(i))) {
                    var livingEnemies = aliveEnemies(good_Fighter);
                    var attacker = initiative_list.get(i);
                    if(livingEnemies.size() != 0) {
                        var chosen_Enemy = livingEnemies.get(rand.nextInt(livingEnemies.size()));

                        if (!attacker.getDead()) Combat.attackAction(attacker, chosen_Enemy);
                    }
                }
                if (good_Fighter.contains(initiative_list.get(i))) {
                    var livingEnemies = aliveEnemies(bad_fighter);

                    var attacker = initiative_list.get(i);
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
            round_counter++;
        } else {
        	System.out.println("Nothing more to fight");
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
