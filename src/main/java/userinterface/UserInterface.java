package userinterface;

import util.Utility;

import java.util.Scanner;

/*
* Focus on user inputs and show game view to user
* */
public class UserInterface {
    private  Scanner scanner;

    public UserInterface(){
        this.scanner   =new Scanner(System.in);
    }

    public void showWelcomeMessage(){
        System.out.println(Utility.MESSAGE_WELCOME_MESSAGE);
    }

    //show the initial grid to user and updated state
    public void displayGrid(char[][] grid){
        System.out.print("  ");
        //display column number at top
        for(int i=1;i<=grid.length;i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int row = 0;row<grid.length;row++){
            //diplay char A for first row and B C .. and other characters for next rows
            System.out.print((char) ('A' + row) + " ");
            for(int col = 0;col<grid[row].length;col++){
                //print values in same line for each entry in grid
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }

    }

    //Input for row character for Like A B C in upper case for next square to open
    public String getUserInput(String prompt){
        System.out.print(prompt);
        return scanner.next();
    }

    public String getUserInput2(String prompt){
        //scanner.close();
        scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    //get number as input from user
    public int getUserInputAsInt(String prompt){
        while (true){
            try {
                System.out.println(prompt);
                return Integer.parseInt(scanner.next());
            }catch (NumberFormatException ex){
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    //get column number from user between ranges of remaining unexplored squares
    public int getUserInputAsInt(String prompt, int min, int max){
        while (true){
            int val = getUserInputAsInt(prompt);
            if(val>=min && val<=max){
                return val;
            }else{
                System.out.println("Input input. please enter a number between " + min + " and " + max + ".");
            }
        }
    }

    public void showMessage(String message){
        System.out.println(message);
    }
}