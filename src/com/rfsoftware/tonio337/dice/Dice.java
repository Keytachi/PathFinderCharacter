package com.rfsoftware.tonio337.dice;

/**
 * Created by adlee on 12/23/2016.
 */

public class Dice {

    private int sides;
    private static final int minSides = 4;

    public Dice(){
        this(minSides);
    }

    public Dice(int sides){
        if (sides < minSides) sides = minSides;
        this.sides = sides;
    }

    public int roll() {
        return roll(sides);
    }

    public static int roll(int numSided){
        return (int) (Math.random()*numSided + 1);
    }

    public static int roll(int numSided, int numDice){
        if (numDice < 1) return 0;

        int sum = 0;

        for (int d = 0; d < numDice; d++)
            sum += roll(numSided);
        return sum;
    }

    @Override
    public String toString() {
        return "D" + sides + " - " + sides + " sided die";
    }
}
