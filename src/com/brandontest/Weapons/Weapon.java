package com.brandontest.Weapons;

import com.brandontest.Secondary.Attribute;

/**
 * Created by Kathy on 12/22/2016.
 */
public class Weapon
{
    private Attribute attribute;

    private int minDamage;
    private int maxDamage;
    private double attkSpeed;
    protected String weapName;

    @Override
    public String toString()
    {
        return weapName;
    }

    public Weapon(Attribute attribute, int minDamage, int maxDamage, double attkSpeed) {
        this.attribute = attribute;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.attkSpeed = attkSpeed;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public double getAttkSpeed() {
        return attkSpeed;
    }
}
