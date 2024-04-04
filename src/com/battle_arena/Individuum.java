package com.battle_arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.battle_arena.enviroment.Battlefield;
import com.battle_arena.enviroment.Tile;
import com.battle_arena.exceptions.OutOfBattlefieldDimensionException;
import com.battle_arena.misc.Position;

abstract public class Individuum implements Comparable {

    private String name;
    private int health;
    //TODO: Spels need Refactoring. (And Implementation :))
    private int speed;
    private int spells;
    private int ac;
    private int attackBonus;
    private int initiativeModifier;
    private List<Weapon> weaponlist = new ArrayList<Weapon>();
    private boolean dead = false;
    private int initiative;
    private Weapon weapon;
    private int strength_modifier;
    private Position position;
    private Battlefield battlefield;

    public Battlefield getBattlefield() {
		return battlefield;
	}

	public void setBattlefield(Battlefield battlefield) {
		this.battlefield = battlefield;
	}

	public void move(int x, int y) throws OutOfBattlefieldDimensionException {
		verify_inside_dimension(this.position.getPos_x() + x, this.position.getPos_y() + y);
		this.position.move(x, y);
	}
	
	public void verify_inside_dimension(int x, int y) throws OutOfBattlefieldDimensionException {
		if (battlefield == null) throw new NullPointerException("There is no Battlefield to be placed on");
		if (
                x < 0 ||
    			x > battlefield.getDim_x()
    		) 	throw new OutOfBattlefieldDimensionException("The x Dimension of the position is to high or to small, place some");
    	if (
    			y < 0 ||
    			y > battlefield.getDim_y()
    		)	throw new OutOfBattlefieldDimensionException("The y Dimension of the position is to high or to small");
	}
	
	public void take_action() {
		
	}
	
    public void setPosition(Position pos) throws OutOfBattlefieldDimensionException {
    	verify_inside_dimension(pos.getPos_x(), pos.getPos_y());
    	battlefield.move_away(this);
    	this.position = pos;
    	battlefield.move_to(this);
    }
    
    public Position getPosition () {
    	return this.position;
    }
    
    public void setXposition(int x) {
    	this.position.x = x;
    }
    
    public void setYposition(int y) {
    	this.position.y = y;
    }
    
    public boolean getDead()
    {
        return this.dead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int compareTo(Individuum e2) {
        Individuum e1 = this;
        if (e2 == null) throw new NullPointerException();
        return e2.getInitiative() - e1.getInitiative();
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getStrength_modifier() {
        return strength_modifier;
    }

    public void setStrength_modifier(int strength_modifier) {
        this.strength_modifier = strength_modifier;
    }

    public Individuum(String name, int health, int spells, int ac, int attackBonus, int initiativeModifier, Weapon weaponlist, int strengthMod) {
        this.name = name;
        this.health = health;
        this.spells = spells;
        this.ac = ac;
        this.attackBonus = attackBonus;
        this.initiativeModifier = initiativeModifier;
        this.weapon = weaponlist;
        this.initiative = initiativeModifier+new Random().nextInt(20)+1;
        this.strength_modifier = strengthMod;
    }

    public int getInitiativeModifier() {
        return initiativeModifier;
    }

    public void setInitiativeModifier(int initiativeModifier) {
        this.initiativeModifier = initiativeModifier;
    }

    public List<Weapon> getWeaponlist() {
        return weaponlist;
    }

    public void setWeaponlist(List<Weapon> weaponlist) {
        this.weaponlist = weaponlist;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if(this.health <= 0) this.dead = true;
    }



    public int getSpells() {
        return spells;
    }

    public void setSpells(int spells) {
        this.spells = spells;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int attackDamage(Weapon weapon) {
        int damage = weapon.getDamage();
        int dice = weapon.getDice();
        int applied_damage = Combat.die(damage) * dice + this.strength_modifier;
        System.out.println(this.name  + " dealt "+ applied_damage+ " damage!");
        return applied_damage;
    }
}
