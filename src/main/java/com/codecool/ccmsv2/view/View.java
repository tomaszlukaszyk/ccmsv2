package com.codecool.ccmsv2.view;

import java.util.Scanner;

    public class View {

        private Scanner scanner;

        public View() {
            scanner = new Scanner(System.in);
        }

        public String getInputString(String text) {
            System.out.print(text);
            return scanner.nextLine();
        }

        public int getInputInt(int start, int end) {
            System.out.printf("Enter a number from %d to %d: ", start, end);
            boolean isCorrect = false;
            int input = 0;
            while(!isCorrect) {
                try{
                    input = Integer.parseInt(scanner.nextLine());

                    if(input  >= start && input <= end) {
                        isCorrect = true;
                    }else {
                        System.out.println("This is not a number from given range. Try again!");
                    }
                }catch(NumberFormatException e) {
                    System.out.println("This is not a number. Try again!");
                }
            }
            return input;
        }

        public void print(String text) {
            System.out.println(text);
        }

        public void printMenu(String... items) {

            System.out.println("What would you like to do: ");

            for (int i=1; i<items.length; i++) {
                System.out.printf("    (%d) %s\n", i, items[i]);
                i++;
            }

            System.out.printf("    (0) %s\n", items[0]);
        }
    }
}
