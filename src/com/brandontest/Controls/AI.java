package com.brandontest.Controls;

/**
 * Created by Brandon on 12/27/2016.
 */

public class AI {

    //TODO Create an AI system that has a logic if it knows it is going to fail then it will heal or flee.

    public static void logicSystem()
    {
        //TODO Create a system where the AI will have a priority list.
        /** Ideal system for the AI
         Healer
         1. If it is a healer (Paladin and Priest), it will try to detect if the player can kill him and if it can they will heal themselves.
         2. If they do not need healing, they will detect all their team to see if a player can kill them and if it can, the healer will make it a priority to heal them.
         3. If they do not need a healer, they will try to attack the lowest health player to try and kill them quick.

         Support
         1. Check the team buffs/debuffs. It will remove or add it to their team.
         2. Check to see if the players team have the debuff. If not then it will apply it to the one with the most (depending on the debuff trait).
         3. Attack if it meet these requirements.

         Damage
         1. Attack the one with the lowest health using their most damge dealing attack.
         2. If they have no more resource, It will do normal attack with weapons.
         */
    }
}
