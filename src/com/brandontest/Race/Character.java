package com.brandontest.Race;
import com.brandontest.Jobtype.*;
import com.brandontest.Secondary.*;
import com.brandontest.Weapons.Weapon;

import java.util.*;

/**
 * Created by Kathy on 12/22/2016.
 */
public abstract class Character
{

    public enum Team
    {GOOD,
     BAD,
     NEUTRAL;

        private static final Team[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static Team getRandomTeam()
        {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }
    public enum Type {PLAYER,NPC}

    public enum Role
    {
        PALADIN,
        WARRIOR;

        private static final Role[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();
        public static Role getRandomRole()
        {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }

    public static ArrayList<Character> characterlist = new ArrayList<Character>();

    JobType jobtype;
    Weapon weapon;
    Attribute attribute;

    protected Team team;
    protected Type playable;
    protected Role role;
    protected String race;

    private String name;
    private int health;
    private int level;


    @Override
    public String toString()
    {
        return race;
    }

    //Superclass when know a specific way
    public Character(JobType jobType, Weapon weapon, int level, String name, Team team, Type playable)
    {
        this.jobtype = jobType;
        this.weapon = weapon;
        this.health = 100;
        this.level = level;
        this.name = name;
        this.team = team;
        this.playable = playable;
        this.attribute = statsCombine();

        switch (team)
        {
            case GOOD :
                break;
            case NEUTRAL:
                break;
            case BAD:
                break;
        }
        switch (playable)
        {
            case PLAYER:
                break;
            case NPC:
                break;
        }
        characterlist.add(this);
    }
    //Superclass when trying to generate randomly
    public Character(Role role, Weapon weapon, int level, String name, Team team, Type playable)
    {

        this.weapon = weapon;
        this.health = 100;
        this.level = level;
        this.name = name;
        this.team = team;
        this.playable = playable;
        this.role = role;


        switch(role)
        {
            case PALADIN:
                this.jobtype = new Paladin();
                break;
            case WARRIOR:
                this.jobtype = new Warrior();
                break;
            default:
        }
        this.attribute = statsCombine();

        switch (team)
        {
            case GOOD :
                break;
            case NEUTRAL:
                break;
            case BAD:
                break;
            default:
        }
        switch (playable)
        {
            case PLAYER:
                break;
            case NPC:
                break;
            default:
        }


        characterlist.add(this);
    }

    //Getter Functions
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getLevel() {
        return level;
    }
    public String getRace() {
        return race;
    }
    public Team getTeam() {
        return team;
    }
    public Role getRole()
    {
        return role;
    }
    public Type getPlayable()
    {
        return playable;
    }
    public JobType getJobtype() {
        return jobtype;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public Attribute getAttribute()
    {
        return attribute;
    }

    //Setter Functions
    public void setHealth(int health){
        this.health += health;
    }

    //Specialty Functions
    public Attribute statsCombine()     //Adding both JobType and Weapon attribute together
    {
        return new Attribute(
                jobtype.getAttribute().getStrength() + weapon.getAttribute().getStrength(),
                jobtype.getAttribute().getIntellect() + weapon.getAttribute().getIntellect(),
                jobtype.getAttribute().getAgility() + weapon.getAttribute().getAgility(),
                jobtype.getAttribute().getStamina() + weapon.getAttribute().getStamina(),
                jobtype.getAttribute().getHaste() + weapon.getAttribute().getHaste(),
                jobtype.getAttribute().getCrit() + weapon.getAttribute().getCrit());

    }
    public void announceStats()         //Display testing
    {
        updateValue();
        findingHealth();
        System.out.println(name + " Attributes :");
        System.out.println("Strength: " + attribute.getStrength());
        System.out.println("Intellect: " + attribute.getIntellect());
        System.out.println("Agility: " + attribute.getAgility());
        System.out.println("Stamina: " + attribute.getStamina());
        System.out.println("Critical Strike: " + String.format("%.2f", attribute.getCrit()));
        System.out.println("Haste: " + String.format("%.2f", attribute.getHaste()));
        System.out.println("Health: " + getHealth());
        System.out.println("Team: " + getTeam());
        System.out.println("Role: " + getRole());
        System.out.println("\n\n");

    }
    public void specialAttribute()      //Each race has a special attribute
    {
        System.out.println("My race has no special attribute");
    }
    public void updateValue()           //Update the value inside of the attribute for the object
    {
        specialAttribute();
        statsCombine();
    }
    public void findingHealth()         //Adding more health since the base health starts at 100
    {
        int healthValue = attribute.getStamina()*10;
        setHealth(healthValue);
        getHealth();
    }
    public void testMoves(Character target) //Testing attack moves
    {
        this.jobtype.spell(this, target);
    }
    public void attack(Character target)    //Sending the parameter
    {
        System.out.println("The strength before passing to the move is: " + attribute.getStrength());
        testMoves(target);
    }

}
