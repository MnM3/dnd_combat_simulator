package com.battle_arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract public class Individuum implements Comparable {

    private String name;
    private int health;
    private int spells;
    private int ac;
    private int attackBonus;
    private int initiativeModifier;
    private List<Weapon> weaponlist = new ArrayList<Weapon>();
    private boolean dead = false;
    private int initiative;
    private Weapon weapon;

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
        if (e1 == null) throw new NullPointerException();
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

    public Individuum(String name,int health, int spells, int ac, int attackBonus, int initiativeModifier, Weapon weaponlist) {
        this.name = name;
        this.health = health;
        this.spells = spells;
        this.ac = ac;
        this.attackBonus = attackBonus;
        this.initiativeModifier = initiativeModifier;
        this.weapon = weaponlist;
        this.initiative = initiativeModifier+new Random().nextInt(20)+1;
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
        int applied_damage = Combat.die(damage) * dice;
        System.out.println(this.name  + " dealt "+ applied_damage+ " damage!");
        return applied_damage;
    }
}
