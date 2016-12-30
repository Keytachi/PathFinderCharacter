package com.brandontest.Jobtype;

import com.brandontest.Controls.IO;
import com.brandontest.Race.Character;
import com.brandontest.Secondary.Attribute;

/**
 * Created by Brandon on 12/22/2016.
 */
public class Paladin extends JobType
{
    private static final double intModifier = 2.8;
    private static final double strModifier = 2.3;
    private static final Attribute baseStats = generateStats(10,3); //Grabbing random number in-between 3-10 for the base model of this class.

    // new Attribute(5,4,2,2,1,1);

    //Constructor
    /**public Paladin()
    {
        this(baseStats); //Loading this variable into the superclass.
    }*/
    public Paladin()
    {
        this(generateStats(20,1));
    }
    //Superclass
    public Paladin(Attribute attribute) {
        super(attribute);
        startBar = 100;
        title = "Paladin";
    }


    //A healing ability from paladin only
    private void holylight()
    {
        subResource(10);
        System.out.println(getResource() + ": " + getStartBar() + "/" + getMaxBar());
    }

    //A damage spell move from paladin only
    private void strike()
    {
        subResource(15);
        System.out.println(getResource() + ": " + getStartBar() + "/" + getMaxBar());

    }

    //Test function
    private void judgement(Character player, Character target)
    {
        if(startBar >= 20)
        {
            int intellect = player.getAttribute().getIntellect() * (int)intModifier;
            int strength = player.getAttribute().getStrength() * (int)strModifier;
            int damage = intellect + strength * 2;
            target.subHealth(damage);
            if(target.getHealth() <= 0)
            {
                System.out.println(target.getName() + " has died");
                target.turnEnd();
                System.out.println(getResource() + ": " + getStartBar() + "/" + getMaxBar());
            }
            System.out.println(player.getName() + " used Judgement on " + target.getName() + " for " + damage + " holy damage.");
            subResource(20);
            System.out.println(getResource() + ": " + getStartBar() + "/" + getMaxBar());

        }else{
            System.out.println("Not enough mana");
            spell(player);

        }
    }
    @Override
    public void spell(Character player)
    {
        IO.printHeader("Paladin Moves Set");
        System.out.println("1. Judgement");
        System.out.println("2. Holy Light");
        System.out.println("3. Holy Strike");
        System.out.println("Choose what spell you want to use: ");

        switch(IO.inputInt())
        {
            case 1:
                judgement(player, player.findTarget());
                break;
            case 2:
                holylight();
                break;
            case 3:
                strike();
                break;
        }

    }
}
