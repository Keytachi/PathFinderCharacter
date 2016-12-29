package com.rfsoftware.tonio337.dice;

public class DiceTest {
    public static void main (String args[]){
        int numDice = 10;

        int minSides = 16;
        int maxSides = 25;

        int sum;

        printHeader("Creating dice array of " + numDice +
                " ranged " + minSides + "-" + maxSides + "...");
        Dice diceArray[] = new Dice[numDice];

        for (int d=0; d < numDice; d++)
            diceArray[d] = new Dice(randomRange(minSides, maxSides));

        printHeader("Rolling the dice array individually...");
        sum = 0;
        for (int d=0; d < numDice; d++){
            int roll = diceArray[d].roll();
            System.out.println("Die " + d + ": " + diceArray[d] +
                    " rolled a " + roll);
            sum += roll;
        }
        System.out.println("The sum is " + sum);


        printHeader("Rolling a " + new Dice(minSides) + " using the static function...");
        System.out.println("The roll is " + Dice.roll(minSides));
    }

    private static int randomRange(int min, int max){
        return (int) (Math.random()*(max-min+1) + min);
    }

    private static void printHeader(String s){
        System.out.println("\n******************************\n"+
                            s+
                            "\n******************************");
    }
}
