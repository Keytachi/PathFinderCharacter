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

    public static enum Team
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

    public static ArrayList<Character> characterList = new ArrayList<Character>();


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
    //protected Choice choice; - Testing for choices

    //Private parameters for the Character class
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
        characterList.add(this);
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
        this.raceComposition = getRaceComposition();


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


        characterList.add(this);
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
    public Race getRaceComposition(){return raceComposition;}

    //Setter Functions
    public void addHealth(int health){
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
        getHealth();
    }
    public void testMoves(Character target) //Testing attack moves
    {
        this.jobtype.spell(this, target);
    }
    public void attack(Character target)    //Function should be a normal attack with the weapon
    {
        System.out.println("The strength before passing to the move is: " + attribute.getStrength());
        testMoves(target);
    }


    public void choices()
    {

        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Inventory");
        System.out.println("4. Run");
        System.out.println("Choose your move: ");

        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        switch (Integer.parseInt(choice))
        {
            case 1:                     //Will call the normal attack function
                break;
            case 2:                     //Will call the spell attack function base off the jobType.
                break;
            case 3:                     //Inventory System later on
                break;
            case 4:                     //Run function to add later on
                run();
                break;
            default:
        }
    }
    /**public static void raceGenerator(String name, Race raceComposition)
    {
        switch(raceComposition)
        {
            case HUMAN:
                //new Human(name);
                break;
            case ELVES:
                System.out.println("I am an elves race");
        }
    }*/

    public void run()                           //Fleeing function
    {
        System.out.println(getName() + " has flee");
        characterList.remove(this);     //Remove from the arrayList. Will add a successful/failure later on.
    }

    public static void descendingOrder()        //Function to sort it descending the arrayList via haste value
    {
        Collections.sort(Character.characterList, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Double.valueOf(o2.getAttribute().getHaste()).compareTo(o1.getAttribute().getHaste());
            }
        });
    }
}
