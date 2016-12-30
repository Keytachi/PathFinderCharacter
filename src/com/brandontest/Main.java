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

    }

    public static void game()
    {
        createPlayer();
        for (Character placeHolder : Character.characterList) {
            placeHolder.announceStats();
        }
        do {
            Character.descendingOrder();
            characterTurn();
        }while(Character.checker() != false);

    }

    public static void createPlayer()   //Generating random character for testing purpose
    {
        Weapon testWep = new Weapon(new Attribute(0,0,0,0,0,0),2,5,2.6 );       //Test Weapon
        int npcGenerate = 2; //(int)(Math.random()*10)+2;                       //From the range of 2-12 since it can randomly only do 1 character at a time.
        Character[] characterNumber = new Character[npcGenerate];               //Creating an object array using the Character class.

        Character test = new Human(new Paladin(new Attribute(999,999,999,999,999,999)),testWep,10,"Test Damage",Character.Team.GOOD, Character.Type.PLAYER);
        for(int i = 0; i < npcGenerate; i++)
        {
            characterNumber[i] = new Human(Character.Role.getRandomRole(),testWep,1, "NPC " + (i+1), getRandomTeam(), Character.Type.PLAYER);
        }
    }

    //Display the order that the special sorting function via haste value.
    public static void characterTurn()
    {
        for(int i = 0; i < Character.characterList.size(); i++)
        {
            Character.characterList.get(i).startTurn();

            /*IO.printHeaderName(Character.characterList.get(i).getName() + " - " + Character.characterList.get(i).getRole() +
                    " - (HEALTH: " + Character.characterList.get(i).getHealth() + "/" + Character.characterList.get(i).getMaxHealth() +
                    " | " + Character.characterList.get(i).getJobtype().getResource() + ": " + Character.characterList.get(i).getJobtype().getStartBar() + "/" + Character.characterList.get(i).getJobtype().getMaxBar() + ")");
                if (Character.characterList.get(i).getPlayable() == Character.Type.PLAYER)                           //Checking to see if the current turn is an NPC or a player.
                {
                    Character.characterList.get(i).choicesMove();
                    if(Character.characterChecker() == true)
                    {
                        i--;
                    }
                }*/
        }
        Character.updateAfter();
    }
}
