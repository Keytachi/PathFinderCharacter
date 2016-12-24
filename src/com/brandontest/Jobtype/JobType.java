package com.brandontest.Jobtype;

import com.brandontest.Secondary.*;
import com.brandontest.Race.Character;

import java.util.Scanner;

/**
 * Created by Kathy on 12/22/2016.
 */
public abstract class JobType implements Ability {

    public enum Resource {RAGE, MANA, ENERGY}
    private Attribute attribute;    //Composition the class Attribute
    private int maxBar;             //Limit of the player bar that the spell can use
    protected int startBar;         //Start of the player bar. Adjust accordingly to the class
    protected String title;         //Variable to override to display the Job type

    //Overriding the toString function from the String to display the title variable
    @Override
    public String toString() {
        return title;
    }

    //Superclass
    public JobType(Attribute attribute) {
        this.attribute = attribute;
        this.maxBar = 100;
    }

    //Constructor
    public JobType()
    {

    }

    //Attribute available for other class to get the information.
    public Attribute getAttribute()
    {
        return attribute;
    }


   /** public void Choices()
    {
        switch(choices)
        case 1:
        Attack();
        break;
        case 2:
        Spell();
        break;
    }**/

    //Empty in case role does not have spells
    public void spell(Attribute damageAttribute)
    {
        System.out.println("You do not have any spell");
    }
    //Empty in case role does not have spells
    public void spell(Character player, Character target)
    {
        System.out.println("You do not have any spell");
    }

    //Function to add a randomize integer to the attribute class.
    public static Attribute generateStats(int a)
    {
        return new Attribute(
                randomStats(a),
                randomStats(a),
                randomStats(a),
                randomStats(a),
                randomStats(a),
                randomStats(a));
        /*
        attribute.setStrength(randomStats(10));
        attribute.setAgility(randomStats(10));
        attribute.setIntellect(randomStats(10));
        attribute.setStamina(randomStats(10));
        attribute.setCrit(randomStats(10));
        attribute.setHaste(randomStats(10));
        */
    }

    //Same function as the top but giving more parameter to give a more precision.
    public static Attribute generateStats(int a, int b) {
        return new Attribute(
                randomStats(a,b),
                randomStats(a,b),
                randomStats(a,b),
                randomStats(a,b),
                randomStats(a,b),
                randomStats(a,b));
    }

    //Randomize from the 1 - range number.
    private static int randomStats(int range)
    {
        return (int)(Math.random()*range)+1;
    }

    //Randomize from the min value - max value.
    private static int randomStats(int max, int min)
    {
        return (int) (Math.random()*(max-min+1)+min);
    }

    //Grab player input to select a move.
    public int choice()
    {
        int answer;
        Scanner enter = new Scanner(System.in);
        System.out.println("What is your choice: ");
        answer = Integer.parseInt(enter.nextLine());

        return answer;
    }

}
