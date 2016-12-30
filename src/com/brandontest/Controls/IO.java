package com.brandontest.Controls;

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
}
