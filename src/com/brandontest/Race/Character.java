package com.brandontest.Race;

import com.brandontest.Controls.BattleSystem;
import com.brandontest.Controls.IO;
import com.brandontest.Jobtype.JobType;
import com.brandontest.Jobtype.Paladin;
import com.brandontest.Jobtype.Warrior;
import com.brandontest.Secondary.Attribute;
import com.brandontest.Gear.Weapon;

import java.util.Random;

/**
 * Created by Brandon on 12/22/2016.
 */
public abstract class Character
{

    public static enum Team
    {GOOD,
     BAD;

        private static final Team[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static Team getRandomTeam()
        {
            return VALUES[RANDOM.nextInt(SIZE)];
        }
    }
    public static enum Type {PLAYER,NPC}

    public static enum Race
    {
        HUMAN,
        ELVES;

        private static final Race[] VALUES = values();
        private static final int SIZE = VALUES.length;
        private static final Random RANDOM = new Random();

        public static Race getRandomRace(){
                return VALUES[RANDOM.nextInt(SIZE)];
        }
    }
    public static enum Role
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

    public static enum Status{
        STUN,
        DEAD,
        ALIVE,
        FLEE
    }

    //public static ArrayList<Character> characterList = new ArrayList<Character>();          //Character list that will be changing.
    //public static ArrayList<Character> tempList = new ArrayList<Character>();               //Character list that is temp and change if there is a change in characterList.


    //Composition of other classes
    JobType jobtype;
    Weapon weapon;
    Attribute attribute;

    //Protected Variables
    protected Team team;
    protected Type playable;
    protected Role role;
    protected String race;
    protected Race raceComposition;
    protected Status statusToGo;
    //protected Choice choice; - Testing for choices

    //Private parameters for the Character class
    private String name;
    private int health;
    private int maxHealth;
    private int level;


    @Override
    public String toString()
    {
        return race;
    }

    //Superclass when know a specific way
    public Character(JobType jobType, Weapon weapon, int level, String name, Team team, Type playable, Role role)
    {
        this.jobtype = jobType;
        this.weapon = weapon;
        this.health = 100;
        this.maxHealth = 100;
        this.level = level;
        this.name = name;
        this.team = team;
        this.playable = playable;
        this.attribute = statsCombine();
        this.statusToGo = Status.ALIVE;
        this.role = role;

        switch(this.role)
        {
            case PALADIN:
                jobtype.setResourceType(this);
                break;
            case WARRIOR:
                jobtype.setResourceType(this);
                break;
            default:
        }
        switch (team)
        {
            case GOOD :
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
        BattleSystem.characterList.add(this);
    }
    //Superclass when trying to generate randomly
    public Character(Role role, Weapon weapon, int level, String name, Team team, Type playable)
    {

        this.weapon = weapon;
        this.health = 100;
        this.maxHealth = 100;
        this.level = level;
        this.name = name;
        this.team = team;
        this.playable = playable;
        this.role = role;
        this.raceComposition = getRaceComposition();
        this.statusToGo = Status.ALIVE;


        switch(role)
        {
            case PALADIN:
                this.jobtype = new Paladin();
                jobtype.setResourceType(this);
                break;
            case WARRIOR:
                this.jobtype = new Warrior();
                jobtype.setResourceType(this);
                break;
            default:
        }
        this.attribute = statsCombine();

        switch (team)
        {
            case GOOD :
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
        BattleSystem.characterList.add(this);
    }

    //Getter Functions
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth()
    {
        return maxHealth;
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
    public Race getRaceComposition(){return raceComposition;}
    public Status getStatusToGo(){
        return statusToGo;
    }

    //Setter Functions
    public void addHealth(int health){
        this.health += health;
    }                                       //Function use for healing spell/inventory.
    public void addMaxHealth(int health){
        this.maxHealth += health;
    }
    public void subHealth(int health)
    {
        this.health -= health;
    }                 //Function use for damaging spell.
    public void subMaxHealth(int health)
    {
        this.maxHealth -= health;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }
    public void setMaxHealth(int health)
    {
        this.maxHealth = health;
    }
    public void setStatusToGo(Status status)
    {
        this.statusToGo = status;
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

    public void specialAttribute()      //Each race has a special attribute
    {
        System.out.println("My race has no special attribute");
    }
    public void updateValue()           //Update the value inside of the attribute for the object
    {
        specialAttribute();
        statsCombine();
    }
    public void findingHealth()         //Adding stamina into health
    {
        int healthValue = attribute.getStamina()*10;
        addHealth(healthValue);
        addMaxHealth(healthValue);
    }

    public void attack(Character target) {
        BattleSystem.attackDamage(this,target);
    }

    //TODO: Create a weight system that will slow down the players haste.

    public void setdebuff()
    {
        //TODO: Create where the debuff will lower an attribute or deals damage over time (per round)
        
    }

    public void setbuff()
    {
        //TODO:Create where the buff will increase an attribute or reduce damage taken.
    }

    public void removeAffect()
    {
        //TODO: Function to remove the buff or debuff from a character
    }

    public void canGo()
    {
        if(characterStatus() == Character.Status.ALIVE)
        {
            IO.actionChoice(this);
        }
        BattleSystem.turnEnd(this);
    }

    public Status characterStatus()
    {
        if(statusToGo == Status.DEAD || statusToGo == Status.STUN)
        {
            return getStatusToGo();
        }
        return getStatusToGo();
    }
}
