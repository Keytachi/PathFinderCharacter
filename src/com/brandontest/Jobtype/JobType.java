package com.brandontest.Jobtype;

import com.brandontest.Race.Character;
import com.brandontest.Secondary.Ability;
import com.brandontest.Secondary.Attribute;

/**
 * Created by Brandon on 12/22/2016.
 */
public abstract class JobType implements Ability {

    public enum Resource {RAGE, MANA, ENERGY}
    private Attribute attribute;    //Composition the class Attribute
    private int maxBar;             //Limit of the player bar that the spell can use
    protected int startBar;         //Start of the player bar. Adjust accordingly to the class
    protected String title;         //Variable to override to display the Job type
    protected Resource resource;

    //Overriding the toString function from the String to display the title variable
    @Override
    public String toString() {
        return title;
    }

    //Superclass
    public JobType(Attribute attribute) {
        this.attribute = attribute;
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

    public void setResourceType(Character standard)
    {
        switch(standard.getRole())
        {
            case PALADIN:
                this.maxBar = 100;
                this.startBar = 100;
                this.resource = Resource.MANA;
                break;
            case WARRIOR:
                this.maxBar = 100;
                this.startBar = 0;
                this.resource = Resource.RAGE;
                break;
        }
    }

    public Resource getResource()
    {
        return resource;
    }

    //Empty in case role does not have spells
    public void spell(Character player)
    {
        System.out.println("You do not have any spell");
        player.choicesMove();
    }

    public void addResource(int value)
    {
        this.startBar += value;
    }

    public void subResource(int value)
    {
        this.startBar -= value;
    }

    public int getStartBar()
    {
        return startBar;
    }
    public int getMaxBar()
    {
        return maxBar;
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
}
