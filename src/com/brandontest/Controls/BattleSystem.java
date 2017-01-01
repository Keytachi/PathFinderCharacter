package com.brandontest.Controls;
import com.brandontest.Race.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Brandon on 12/30/2016.
 */
public class BattleSystem {

    public static ArrayList<Character> characterList = new ArrayList<Character>();

    public static void descendingOrder(){
        Collections.sort(characterList, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Double.valueOf(o2.getAttribute().getHaste()).compareTo(o1.getAttribute().getHaste());
            }
        });
    }   //Function to sort it descending the arrayList via haste value


    public static void turnEnd(Character player)
    {
        if(player.getHealth() <= 0)
        {
            player.setStatusToGo(Character.Status.DEAD);
        }

    }

    public static void updateAfter()
    {
        for(int i = 0; i < characterList.size(); i++)
        {
            if(characterList.get(i).getStatusToGo() == Character.Status.FLEE)
                characterList.remove(characterList.get(i));
        }
        descendingOrder();

    }

    public static boolean checker() {                           //This function will be use to check to see if the game is over or not. This will depend if there are any more players on an opposing team.
        int bad = 0;
        int good = 0;

        for(int i = 0; i < characterList.size(); i++)
        {
            if(characterList.get(i).getTeam() == Character.Team.BAD && characterList.get(i).getStatusToGo() != Character.Status.DEAD)
            {
                bad++;
            }
            if(characterList.get(i).getTeam() == Character.Team.GOOD && characterList.get(i).getStatusToGo() != Character.Status.DEAD)
            {
                good++;
            }
        }
        if(good == 0 || bad == 0)
            return false;
        else
            return true;
    }

    //Display the order that the special sorting function via haste value.
    public static void characterTurn()
    {
        for(int i = 0; i < characterList.size(); i++)
        {
            characterList.get(i).canGo();
        }
        updateAfter();
    }


    public static void attackDamage(Character player, Character target)
    {
        int str = (int)(player.getAttribute().getStrength() * 2.8);
        int min = player.getWeapon().getMinDamage();
        int max = player.getWeapon().getMaxDamage();
        int damage = (int) ((Math.random()*(max - min)+min)+str);
        target.subHealth(damage);
        IO.damageReport(player, target, damage);
    }
}
