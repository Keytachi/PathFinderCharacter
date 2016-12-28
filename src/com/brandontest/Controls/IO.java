package com.brandontest.Controls;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Brandon on 12/22/2016.
 */
public class IO
{

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

}
