package com.brandontest;

import com.brandontest.Jobtype.Paladin;
import com.brandontest.Race.Character;
import com.brandontest.Race.Human;
import com.brandontest.Secondary.Attribute;
import com.brandontest.Weapons.Weapon;

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
        for (Character placeHolder : Character.characterList) {
            placeHolder.announceStats();
        }
        do {
            Character.descendingOrder();
            Character.update();
            displayOrder();
            //updateCharacter();
            Character.update();
            Character.descendingOrder();
        }while(gameOver() != false);

    }

    public static void createPlayer()   //Generating random character for testing purpose
    {
        Weapon testWep = new Weapon(new Attribute(0,0,0,0,0,0),2,5,2.6 );       //Test Weapon
        int npcGenerate = (int)(Math.random()*10)+2;                            //From the range of 2-12 since it can randomly only do 1 character at a time.
        Character[] characterNumber = new Character[npcGenerate];               //Creating an object array using the Character class.
        Character npcTest = new Human(new Paladin(), testWep, 1, "Brandon", getRandomTeam(), Character.Type.NPC);
        for(int i = 0; i < npcGenerate; i++)
        {
            characterNumber[i] = new Human(Character.Role.getRandomRole(),testWep,1, "NPC " + (i+1), getRandomTeam(), Character.Type.PLAYER);
            //characterNumber[i] = Character.raceGenerator("NPC " + (i+1), Character.Race.getRandomRace());
        }
    }

    //Display the order that the special sorting function via haste value.
    public static void displayOrder()
    {
        for(int i = 0; i < Character.characterList.size(); i++)
        {
            if(Character.characterList.get(i).getPlayable() == Character.Type.NPC)
            {
                continue;
            }
            else {

                System.out.println(Character.characterList.get(i).getName() + " moves with the haste value of " + Character.characterList.get(i).getAttribute().getHaste());
                if (Character.characterList.get(i).getPlayable() == Character.Type.PLAYER)                           //Checking to see if the current turn is an NPC or a player.
                {
                    Character.characterList.get(i).choices();
                }
            }
        }
    }

    public static boolean gameOver()
    {
        if(Character.checker() == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
