package com.brandontest.Race;

import com.brandontest.Jobtype.JobType;
import com.brandontest.Jobtype.Paladin;
import com.brandontest.Jobtype.Warrior;
import com.brandontest.Secondary.Attribute;
import com.brandontest.Weapons.Weapon;

import java.util.*;

/**
 * Created by Kathy on 12/22/2016.
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
    /**public enum Choice
    {
        ATTACK,
        SPELL,
        INVENTORY,
        RUN;
    }*/

    public static ArrayList<Character> characterList = new ArrayList<Character>();
    public static ArrayList<Character> tempList = new ArrayList<Character>();


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
    public void subHealth(int health)
    {
        this.health -= health;
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
        getHealth();
    }
    /*public void testMoves(Character target) //Testing attack moves
    {
        this.jobtype.spell(this, target);
    }*/
    public void attack(Character target)    //Function should be a normal attack with the weapon
    {
        int str = (int)(attribute.getStrength() * .68);
        int min = weapon.getMinDamage();
        int max = weapon.getMaxDamage();

        int health = target.getHealth();

        int damage = (int) ((Math.random()*(max - min)+min)+str);

        System.out.println(target.getName() + " Health Pool is: " + target.getHealth());
        System.out.println(getName() + " is attacking "  + target.getName() + " for " + damage + " physical damage.");
        target.subHealth(damage);
        if(health <= 0)
        {
            System.out.println(target.getName() + " has died");
            System.out.println(target.getName() + " remaining health is: " + target.getHealth());
            characterList.remove(target);
            update();
        }
        else System.out.println(target.getName() + " remaining health is: " + target.getHealth());

    }


    public void choices()
    {

        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Inventory");
        System.out.println("4. Run");
        System.out.println("Choose your move: ");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice)
        {
            case 1:                     //Will call the normal attack function
                attack(findTarget());
                break;
            case 2:                     //Will call the spell attack function base off the jobType.
                jobtype.spell(this,findTarget());
                break;
            case 3:                     //Inventory System later on
                break;
            case 4:                     //Run function to add later on
                run();
                break;
            default:
                System.out.println("Please choose within the limit of 1-4");
        }
    }

    public Character findTarget()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose a target: ");
        for(int i = 0; i < characterList.size(); i++)
        {
            System.out.println((i+1) + ". " + characterList.get(i).getName() + " = " + characterList.get(i).getTeam());
        }
        int choice = input.nextInt();

        return targetSystem(choice);


    }
    public Character targetSystem(int choice)
    {
        return characterList.get(choice-1);
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
        update();
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

    public static boolean checker()
    {
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

    public static void update()
    {
        if(characterList.size() != tempList.size())
        {
            listCopy();
            descendingOrder();
        }
        else
            System.out.println("There was no changes");
    }

    public static void listCopy()
    {
        tempList = (ArrayList<Character>)characterList.clone();
    }

    public static boolean characterChecker()
    {
        if(characterList.size() != tempList.size())
        {
            return true;
        }
        else return false;
    }

}
