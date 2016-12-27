package com.brandontest.Secondary;

/**
 * Created by Brandon on 12/22/2016.
 */
public class Attribute
{
    private int strength;
    private int intellect;
    private int agility;
    private int stamina;
    private double haste;
    private double crit;

    public Attribute(int strength, int intellect, int agility, int stamina, double haste, double crit) {
        this.strength = strength;
        this.intellect = intellect;
        this.agility = agility;
        this.stamina = stamina;
        this.haste = haste;
        this.crit = crit;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntellect() {
        return intellect;
    }

    public int getAgility() {
        return agility;
    }

    public int getStamina() {
        return stamina;
    }

    public double getHaste() {
        return haste;
    }

    public double getCrit() {
        return crit;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setHaste(double haste) {
        this.haste = haste;
    }

    public void setCrit(double crit) {
        this.crit = crit;
    }
}
