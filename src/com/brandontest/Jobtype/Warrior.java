package com.brandontest.Jobtype;

import com.brandontest.Controls.IO;
import com.brandontest.Race.Character;
import com.brandontest.Secondary.Attribute;

/**
 * Created by Brandon on 12/22/2016.
 */
public class Warrior extends JobType
{
    private static final Attribute baseStats = generateStats(10,3); //Grabbing random number in-between 3-10 for the base model

    //Constructor
    /**public Warrior()
    {
        this(baseStats); //Loading this variable into the superclass.
    }*/
    public Warrior()
    {
        this(generateStats(12,3));
    }

    //Superclass
    public Warrior(Attribute attribute) {
        super(attribute);
        startBar = 0;
        title = "Warrior";
    }

    //Polymorphism of the Spell function
    @Override
    public void spell(Character player, Character target)
    {
        switch(IO.inputInt())
        {
            case 1:
                rend(player,target);
                break;
            case 2:
                charge(player,target);
                break;
            case 3:
                wound(player,target);
                break;
        }

    }

    //A special spell move from warrior only
    private int rend(Character player, Character target)
    {
        System.out.println("Strength that is being pass is: " + player.getAttribute().getStrength());
        System.out.println("You press rend");
        startBar=-30;
        return 1;
    }

    //A healing ability from warrior only
    private int charge(Character player, Character target)
    {
        System.out.println("You press charge");
        startBar=+10;
        return 1;
    }

    //A damage spell move from warrior only
    private int wound(Character player, Character target)
    {
        System.out.println("You press wound");
        startBar=-15;
        return 1;
    }
}
