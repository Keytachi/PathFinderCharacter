package com.brandontest.Controls;

import com.brandontest.Race.Character;
import com.rfsoftware.tonio337.dice.Dice;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Brandon on 12/22/2016.
 */
public class IO
{

    public static Dice d20 = new Dice(20);
    public static Dice d12 = new Dice(12);
    public static Dice d10 = new Dice(10);
    public static Dice d8 = new Dice(8);
    public static Dice d6 = new Dice(6);
    public static Dice d4 = new Dice();

    public static int inputInt()
    {
        Scanner input = new Scanner(System.in);

        try{
            int choice = input.nextInt();
            return choice;
        }catch(InputMismatchException e){
            System.out.println("Choose again but with a integer!!!!");
            return inputInt();
        }
    }

    public static void printHeader(String s) {
        System.out.println("\n******************************\n" +
                s +
                "\n******************************");

    }
    public static void printHeaderName(String s) {
        System.out.println("\n************************************************\n" +
                s +
                "\n************************************************");

    }

    public static Character findTarget() {

        IO.printHeader("Character List");
        for(int i = 0; i < BattleSystem.characterList.size(); i++)
        {
            System.out.println((i+1) + ". " + BattleSystem.characterList.get(i).getName() + " = " + BattleSystem.characterList.get(i).getTeam() + " - " + BattleSystem.characterList.get(i).getStatusToGo());
        }

        System.out.println("Choose a target: ");
        int value = IO.inputInt();
        if(value-1 < BattleSystem.characterList.size()+1 && value > 0) {
            if(BattleSystem.characterList.get(value - 1).getStatusToGo() == Character.Status.DEAD || BattleSystem.characterList.get(value - 1).getStatusToGo() == Character.Status.FLEE)
            {
                System.out.println("Target can't be attack.");
                return findTarget();
            }
            return BattleSystem.characterList.get(value-1);
        }
        else {
            System.out.println("Please choose within the limits!");
            return findTarget();
        }
    }

    public static void actionChoice(Character player) {
        nameHeader(player);
        System.out.println("1. Attack");
        System.out.println("2. Spell");
        System.out.println("3. Gear");
        System.out.println("4. Run");
        System.out.println("Choose your move: ");

        switch (IO.inputInt())
        {
            case 1:                     //Will call the normal attack function
                player.attack(findTarget());
                break;
            case 2:                     //Will call the spell attack function base off the jobType.
                player.getJobtype().spell(player);
                break;
            case 3:                     //Gear System later on
                break;
            case 4:                     //Run function
                run(player);
                break;
            default:
                System.out.println("Please choose within the limit of 1-4 ");
                actionChoice(player);          //Continuous loop if the right press is not correct.
        }
    }

    public static void announceStats(Character player)         //Display attribute
    {
        player.updateValue();
        player.findingHealth();
        System.out.println(player.getName() + " Attributes:");
        System.out.println("Strength: " + player.getAttribute().getStrength());
        System.out.println("Intellect: " + player.getAttribute().getIntellect());
        System.out.println("Agility: " + player.getAttribute().getAgility());
        System.out.println("Stamina: " + player.getAttribute().getStamina());
        System.out.println("Critical Strike: " + String.format("%.2f", player.getAttribute().getCrit()));
        System.out.println("Haste: " + String.format("%.2f", player.getAttribute().getHaste()));
        System.out.println("Health: " + player.getHealth());
        System.out.println("Team: " + player.getTeam());
        System.out.println("Role: " + player.getRole());
        System.out.println("Type of Player: " + player.getPlayable());
        System.out.println("\n\n");
    }

    public static void nameHeader(Character player)
    {
        IO.printHeaderName(player.getName() + " - " + player.getRole() +
                " - (HEALTH: " + player.getHealth() + "/" + player.getMaxHealth() +
                " | " + player.getJobtype().getResource() + ": " + player.getJobtype().getStartBar() + "/" + player.getJobtype().getMaxBar() + ")");
    }

    public static void damageReport(Character player, Character target, int damage)
    {
        System.out.println(target.getName() + " Health Pool is: " + target.getHealth());
        if(player.getName() == target.getName())
        {
            System.out.println(player.getName() + " attack themselves out of confusion for: " + damage + " physical damage.");
        }
        else if(player.getTeam() == target.getTeam())
        {
            System.out.println(player.getName() + " has attack their teammate for: " + damage + " physical damage.");
        }
        else{System.out.println(player.getName() + " is attacking "  + target.getName() + " for " + damage + " physical damage.");}

        if(player.getRole() == Character.Role.WARRIOR)
        {
            player.getJobtype().addResource(5);
        }
        if(target.getHealth() <= 0)
        {
            IO.deathLog(target);
        }
        else System.out.println(target.getName() + " remaining health is: " + target.getHealth());
    }

    public static void deathLog(Character target)
    {
        System.out.println(target.getName() + " has died");
        target.setStatusToGo(Character.Status.DEAD);
    }

    public static void run(Character player) {
        System.out.println(player.getName() + " has flee");
        player.setStatusToGo(Character.Status.FLEE);
    }
}
