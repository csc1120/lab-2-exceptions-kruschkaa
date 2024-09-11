/**
 * Course: CSC1020 - 121
 * Fall 2024
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Alexander Kruschka
 * Last Updated: 9/11/2024
 */

package kruschkaa;

import java.util.Scanner;

/**
 *
 */
public class Driver {

    static final int MIN_DICE = 2;
    static final int MAX_DICE = 10;

    public static void main(String[] args) {

        int[] input = new int[0];
        Die[] dice;

        boolean validInput = false;
        while (!validInput) {
            input = getInput();
            if (input[0] < MIN_DICE || input[0] > MAX_DICE) {
                System.out.println("Bad die creation: Illegal number of die: " + input[0]);
            } else {
                try {
                    dice = createDice(input[0], input[1]);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }



    }

    /**
     * Asks the user to enter 3 whole numbers, the number of dice, the number
     * of sides, and the number of rolls, and returns that as an array.
     * @return a 3 integer array from user input.
     */
    private static int[] getInput() {

        Scanner scan = new Scanner(System.in);
        int[] intArray = new int[3];
        boolean isValid = false;

        while (!isValid) {
            System.out.println("""
                    Please enter the number of dice to roll, how many sides the dice have,
                    and how many rolls to complete, separating the values by a space.
                    Example: "2 6 1000"\s""");

            System.out.println("\nEnter configuration: ");
            String userInput = scan.nextLine();
            String[] strArray = userInput.split(" ");

            try {
                if (strArray.length != 3) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] = Integer.parseInt(strArray[i]);
                }
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input: " +
                        "Expected 3 values but received " + strArray.length);
            }
        }

        return intArray;
    }

    /**
     * Takes in two arguments and uses them to create an array
     * of Die objects, which is returned.
     * @param numDice is the number of dice to create.
     * @param numSides is the number of sides per die.
     * @return an array of Die based on input.
     */
    private static Die[] createDice(int numDice, int numSides) {
        Die[] die = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            die[i] = new Die(numSides);
        }
        return die;
    }

}