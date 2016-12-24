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
        for(Character placeHolder : Character.characterList)
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
            characterNumber[i] = new Human(Character.Role.getRandomRole(),testWep,1, "NPC " + (i+1), getRandomTeam(), Character.Type.PLAYER);
            //characterNumber[i] = Character.raceGenerator("NPC " + (i+1), Character.Race.getRandomRace());
        }
    }


}
