package com.brandontest;

import com.brandontest.Jobtype.*;
import com.brandontest.Race.*;
import com.brandontest.Race.Character;
import com.brandontest.Secondary.*;
import com.brandontest.Weapons.*;

public class Main {

    public static void main(String[] args)
    {

        game();

        /*
        Weapon baseWep = new Weapon(new Attribute(6,8,20,15,2,1),2,10,2.6);
        Human playerNPC = new Human(new Paladin(),baseWep, 1, "Brandon", Character.Team.GOOD, Character.Type.PLAYER);
        Human playerNPC2 = new Human(new Warrior(), baseWep, 99, "Test NPC", Character.Team.BAD, Character.Type.NPC);


        for(Character placeHolder : Character.characterlist)
        {
            placeHolder.announceStats();
            placeHolder.attack(playerNPC2);
        }*/
    }

    public static void game()
    {
        createPlayer();
        for(Character placeHolder : Character.characterlist)
        {
            placeHolder.announceStats();
        }

    }

    public static void createPlayer()
    {
        Weapon testWep = new Weapon(new Attribute(0,0,0,0,0,0),2,5,2.6 );
        int npcGenerate = (int)(Math.random()*10)+1;
        Character[] characterNumber = new Character[npcGenerate];
        for(int i = 0; i < npcGenerate; i++)
        {
            //characterNumber[i] = new Human(Character.Role.getRandomRole(),testWep,1, "NPC " + (i+1), Character.Team.getRandomTeam(), Character.Type.PLAYER);
            characterNumber[i] = new Human("NPC " + (i+1));
        }
    }

    public static void raceRandomizer()
    {

    }

}
