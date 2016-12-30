package com.brandontest.Race;

import com.brandontest.Controls.IO;
import com.brandontest.Jobtype.JobType;
import com.brandontest.Jobtype.Paladin;
import com.brandontest.Jobtype.Warrior;
import com.brandontest.Secondary.Attribute;
import com.brandontest.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Brandon on 12/22/2016.
 */
public abstract class Character
{
    private static final double strModifier = 1.20;

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
    /**public enum Choice
    {
        ATTACK,
        SPELL,
        INVENTORY,
        RUN;
    }*/

    public static enum Status{
        STUN,
        DEAD,
        ALIVE,
        FLEE
    }

    public static ArrayList<Character> characterList = new ArrayList<Character>();          //Character list that will be changing.
    public static ArrayList<Character> tempList = new ArrayList<Character>();               //Character list that is temp and change if there is a change in characterList.


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
    public Character(JobType jobType, Weapon weapon, int level, String name, Team team, Type playable)
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


        switch (team)
        {
            case GOOD :
                break;
            /*case NEUTRAL:
                break;*/
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
        characterList.add(this);
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
            /*case NEUTRAL:
                break;*/
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


        characterList.add(this);
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
    public void announceStats()         //Display attribute
    {
        updateValue();
        findingHealth();
        System.out.println(name + " Attributes:");
        System.out.println("Strength: " + attribute.getStrength());
        System.out.println("Intellect: " + attribute.getIntellect());
        System.out.println("Agility: " + attribute.getAgility());
        System.out.println("Stamina: " + attribute.getStamina());
        System.out.println("Critical Strike: " + String.format("%.2f", attribute.getCrit()));
        System.out.println("Haste: " + String.format("%.2f", attribute.getHaste()));
        System.out.println("Health: " + getHealth());
        System.out.println("Team: " + getTeam());
        System.out.println("Role: " + getRole());
        System.out.println("Type of Player: " + getPlayable());
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
    public void findingHealth()         //Adding stamina into health
    {
        int healthValue = attribute.getStamina()*10;
        addHealth(healthValue);
        addMaxHealth(healthValue);
    }

    public void attack(Character target) {
        int str = (int)(attribute.getStrength() * strModifier);
        int min = weapon.getMinDamage();
        int max = weapon.getMaxDamage();
        int damage = (int) ((Math.random()*(max - min)+min)+str);

        System.out.println(target.getName() + " Health Pool is: " + target.getHealth());
        if(getName() == target.getName())
        {
            System.out.println(getName() + " attack themselves out of confusion for: " + damage + " physical damage.");
        }
        else if(getTeam() == target.getTeam())
        {
            System.out.println(getName() + " has attack their teammate for: " + damage + " physical damage.");
        }
        else{System.out.println(getName() + " is attacking "  + target.getName() + " for " + damage + " physical damage.");}
        target.subHealth(damage);

        if(role == Role.WARRIOR)
        {
            jobtype.addResource(5);
        }

        if(target.getHealth() <= 0)
        {
            System.out.println(target.getName() + " has died");
            target.turnEnd();
            System.out.println(jobtype.getResource() + ": " + jobtype.getStartBar() + "/" + jobtype.getMaxBar());
        }
        else System.out.println(target.getName() + " remaining health is: " + target.getHealth());
        System.out.println(jobtype.getResource() + ": " + jobtype.getStartBar() + "/" + jobtype.getMaxBar());

    }

    public void choicesMove() {
        IO.printHeaderName(getName() + " - " + getRole() +
                " - (HEALTH: " + getHealth() + "/" + getMaxHealth() +
                " | " + jobtype.getResource() + ": " + jobtype.getStartBar() + "/" + jobtype.getMaxBar() + ")");
        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Inventory");
        System.out.println("4. Run");
        System.out.println("Choose your move: ");

        switch (IO.inputInt())
        {
            case 1:                     //Will call the normal attack function
                attack(findTarget());
                break;
            case 2:                     //Will call the spell attack function base off the jobType.
                jobtype.spell(this);
                break;
            case 3:                     //Inventory System later on
                break;
            case 4:                     //Run function
                run();
                break;
            default:
                System.out.println("Please choose within the limit of 1-4 ");
                choicesMove();          //Continuous loop if the right press is not correct.
        }
    }
    public Character findTarget() {

        IO.printHeader("Character List");
        for(int i = 0; i < characterList.size(); i++)
        {
            System.out.println((i+1) + ". " + characterList.get(i).getName() + " = " + characterList.get(i).getTeam() + " - " + characterList.get(i).getStatusToGo());
        }

        System.out.println("Choose a target: ");
        int value = IO.inputInt();
        if(value < characterList.size()+1 && value > 0) {
            if(characterList.get(value - 1).getStatusToGo() == Status.DEAD || characterList.get(value - 1).getStatusToGo() == Status.FLEE)
                {
                    System.out.println("Target can't be attack.");
                    return findTarget();
                }
            return targetSystem(value);
        }
        else {
            System.out.println("Please choose within the limits!");
            return findTarget();
        }
    }
    public Character targetSystem(int choice)
    {
        return characterList.get(choice-1);
    }

    public void run() {
        System.out.println(getName() + " has flee");
        statusToGo = Status.FLEE;
    }
    public static void descendingOrder(){
        Collections.sort(Character.characterList, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Double.valueOf(o2.getAttribute().getHaste()).compareTo(o1.getAttribute().getHaste());
            }
        });
    }   //Function to sort it descending the arrayList via haste value
    public static boolean checker() {                           //This function will be use to check to see if the game is over or not. This will depend if there are any more players on an opposing team.
        int bad = 0;
        int good = 0;

        for(int i = 0; i < characterList.size(); i++)
        {
            if(characterList.get(i).getTeam() == Team.BAD)
            {
                bad++;
            }
            if(characterList.get(i).getTeam() == Team.GOOD)
            {
                good++;
            }
        }
        if(good == 0 || bad == 0)
        {
            good = 0;
            bad = 0;
            return false;
        }
        else{
            return true;
        }
    }
    /**public void update() {                           //Function to compare the tempList to the characterList and if there are any changes, update the order of the players again.
        if(characterList.size() != tempList.size())
        {
            descendingOrder();
        }
        else
            System.out.println("There was no changes");
    }
    public static void listCopy()
    {
        tempList = (ArrayList<Character>)characterList.clone();
    }       //Function copy the characterList to tempList.

    public static boolean characterChecker() {      //Check to see if there is a difference between tempList and characterList to update or not.
        if(characterList.size() != tempList.size())
        {
            listCopy();
            return true;
        }
        else return false;
    }*/

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

    public void startTurn()
    {
        if(characterStatus() == Status.ALIVE)
        {
            choicesMove();
        }
        turnEnd();
    }

    public Status characterStatus()
    {
        if(statusToGo == Status.DEAD || statusToGo == Status.STUN)
        {
            return getStatusToGo();
        }
        return getStatusToGo();
    }

    public void turnEnd()
    {
        if(getHealth() <= 0)
        {
            statusToGo = Status.DEAD;
        }

    }

    public static void updateAfter()
    {
        for(int i = 0; i < characterList.size(); i++)
        {
            if(characterList.get(i).statusToGo == Status.FLEE)
            characterList.remove(characterList.get(i));
        }
        descendingOrder();

    }
}
