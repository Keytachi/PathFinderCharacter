package com.brandontest.Jobtype;

import com.brandontest.Race.Character;
import com.brandontest.Secondary.Attribute;

/**
 * Created by Brandon on 12/22/2016.
 */
public class Paladin extends JobType
{
    private static final Attribute baseStats = generateStats(10,3); //Grabbing random number in-between 3-10 for the base model of this class.

    // new Attribute(5,4,2,2,1,1);

    //Constructor
    /**public Paladin()
    {
        this(baseStats); //Loading this variable into the superclass.
    }*/
    public Paladin()
    {
        this(generateStats(12,3));
    }
    //Superclass
    public Paladin(Attribute attribute) {
        super(attribute);
        startBar = 100;
        title = "Paladin";
    }
    //Polymorphism of the Spell function
    @Override
    public void spell(Attribute damageAttribute)
    {
        switch(choice())
        {
            case 1:
                judgement(damageAttribute);
                break;
            case 2:
                holylight();
                break;
            case 3:
                strike();
                break;
        }

    }
    //A special spell move from paladin only
    private int judgement(Attribute damageAttribute)
    {
        if(startBar > 0)
        {

            int intel = (damageAttribute.getIntellect()*2);
            int str = (int)(damageAttribute.getStrength()*.5);


            startBar=-30;
            return 1;
        }else{
            System.out.println("Not enough mana");
            return 1;
        }
    }

    //A healing ability from paladin only
    private int holylight()
    {
        startBar=-10;
        return 1;
    }

    //A damage spell move from paladin only
    private int strike()
    {
        startBar=-15;
        return 1;
    }

    //Test function
    private int judgement(Character player, Character target)
    {
        if(startBar > 0)
        {
            System.out.println("You press Judge");
            System.out.println(target.getName() + " is the target.");
            System.out.println(player.getName() + " is the player");
            startBar=-30;
            return 1;
        }else{
            System.out.println("Not enough mana");
            return 1;
        }
    }
    @Override
    public void spell(Character player, Character target)
    {
        switch(choice())
        {
            case 1:
                judgement(player, target);
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
