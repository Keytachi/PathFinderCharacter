package com.brandontest;

import com.brandontest.Race.Character;
import com.brandontest.Race.Human;
import com.brandontest.Secondary.*;
import com.brandontest.Weapons.*;


import static com.brandontest.Race.Character.Team.getRandomTeam;

public class Main {

    public static void main(String[] args)
    {

        game();

        /*
        Weapon baseWep = new Weapon(new Attribute(6,8,20,15,2,1),2,10,2.6);
        Human playerNPC = new Human(new Paladin(),baseWep, 1, "Brandon", Character.Team.GOOD, Character.Type.PLAYER);
        Human playerNPC2 = new Human(new Warrior(), baseWep, 99, "Test NPC", Character.Team.BAD, Character.Type.NPC);


        for(Character placeHolder : Character.characterList)
        {
            placeHolder.announceStats();
            placeHolder.attack(playerNPC2);
        }*/
    }

    public static void game()
    {
        createPlayer();
        //do {
            for (Character placeHolder : Character.characterList) {
                placeHolder.announceStats();
            }
            Character.descendingOrder();
            displayOrder();
        //}while(gameOver() != false);

    }

    public static void createPlayer()   //Generating random character for testing purpose
    {
        Weapon testWep = new Weapon(new Attribute(0,0,0,0,0,0),2,5,2.6 );       //Testing Weapon
        int npcGenerate = (int)(Math.random()*10)+2;                            //From the range of 2-12 since it can randomly only do 1 character at a time.
        Character[] characterNumber = new Character[npcGenerate];               //Creating an object array using the Character class.
        for(int i = 0; i < npcGenerate; i++)
        {
            characterNumber[i] = new Human(Character.Role.getRandomRole(),testWep,1, "NPC " + (i+1), getRandomTeam(), Character.Type.PLAYER);
            //characterNumber[i] = Character.raceGenerator("NPC " + (i+1), Character.Race.getRandomRace());
        }
    }

    /*public static void descendingOrder()
    {
        Collections.sort(Character.characterList, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Double.valueOf(o2.getAttribute().getHaste()).compareTo(o1.getAttribute().getHaste());
            }
        });
    }*/

    //Display the order that the special sorting function via haste value.
    public static void displayOrder()
    {
        for(int i = 0; i < Character.characterList.size(); i++)
        {
            System.out.println(Character.characterList.get(i).getName() + " moves with the haste value of " + Character.characterList.get(i).getAttribute().getHaste());
            if(Character.characterList.get(i).getPlayable() == Character.Type.PLAYER)                           //Checking to see if the current turn is an NPC or a player.
            {
                Character.characterList.get(i).choices();
            }
        }
    }


}
