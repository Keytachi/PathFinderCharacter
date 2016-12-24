package com.brandontest.Race;

import com.brandontest.Jobtype.JobType;
import com.brandontest.Weapons.Weapon;

//import com.brandontest.Secondary.Attribute;
//import com.brandontest.Secondary.Attribute;

/**
 * Created by Kathy on 12/22/2016.
 */
public class Human extends Character
{
    private double critModifier;

    public Human(Role role, Weapon weapon,int level,String name, Team team, Type playable) {
        super(role, weapon, level, name, team, playable);
        race = "Human";
        this.critModifier = .05;
    }
    public Human(JobType jobtype, Weapon weapon,int level,String name, Team team, Type playable) {
        super(jobtype, weapon, level, name, team, playable);
        race = "Human";
        this.critModifier = .05;
    }

    /**public Human(String name)
    {
        super(Character.Role.getRandomRole(),new Weapon(new Attribute(0,0,0,0,0,0), 2, 12, 2.6),
                                                        1, name,
                                                        Character.Team.getRandomTeam(),
                                                        Character.Type.PLAYER);
    }*/

    @Override
    public void specialAttribute()
    {
        double newCrit = (attribute.getCrit()*critModifier)+1;
        attribute.setCrit(newCrit);
    }

}
