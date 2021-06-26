package com.battle_arena;

public class Weapon {
    private int damage;
    private int dice;
    private String name;
    private int crit_multi;
    private int crit_chance;

    public Weapon(int damage, int dice, String name, int crit_multi, int crit_chance) {
        this.damage = damage;
        this.dice = dice;
        this.name = name;
        this.crit_multi = crit_multi;
        this.crit_chance = crit_chance;
    }


    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCrit_multi() {
        return crit_multi;
    }

    public void setCrit_multi(int crit_multi) {
        this.crit_multi = crit_multi;
    }

    public int getCrit_chance() {
        return crit_chance;
    }

    public void setCrit_chance(int crit_chance) {
        this.crit_chance = crit_chance;
    }
}
