package com.brandontest.Jobtype;

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
    public void spell(Attribute damageAttribute)
    {
        switch(choice())
        {
            case 1:
                rend(damageAttribute);
                break;
            case 2:
                charge(damageAttribute);
                break;
            case 3:
                wound(damageAttribute);
                break;
        }

    }

    //A special spell move from warrior only
    private int rend(Attribute damageAttribute)
    {
        System.out.println("Strength that is being pass is: " + damageAttribute.getStrength());
        System.out.println("You press rend");
        startBar=-30;
        return 1;
    }

    //A healing ability from warrior only
    private int charge(Attribute damageAttribute)
    {
        System.out.println("You press charge");
        startBar=+10;
        return 1;
    }

    //A damage spell move from warrior only
    private int wound(Attribute damageAttribute)
    {
        System.out.println("You press wound");
        startBar=-15;
        return 1;
    }
}
