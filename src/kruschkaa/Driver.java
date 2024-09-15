/**
 * Course: CSC1020 - 121
 * Fall 2024
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Alexander Kruschka
 * Last Updated: 9/15/2024
 */

package kruschkaa;

import java.util.Scanner;

/**
 * This program takes user inputted data and creates a number of dice with
 * a number of sides and rolls them a number of times. It adds the dice together,
 * then counts the frequency that each total was rolled. Lastly, using this data,
 * it displays the number of times each possible outcome was rolled.
 */
public class Driver {

    //Static Variables
    static final int MIN_DICE = 2;
    static final int MAX_DICE = 10;

    public static void main(String[] args) {

        //Variables
        int[] input;
        Die[] dice = new Die[0];
        int[] rolls;
        int numDice = 0;
        int numSides = 0;
        int numRolls = 0;
        int max;

        //Acquire a valid input of dice, sides, and total rolls
        boolean validInput = false;
        while (!validInput) {
            input = getInput();
            numDice = input[0];
            numSides = input[1];
            numRolls = input[2];
            if (numDice >= MIN_DICE && numDice <= MAX_DICE) {
                try {
                    dice = createDice(numDice, numSides);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Bad die creation: Illegal number of die: " + input[0]);
            }
        }

        //Roll the dice per user input
        rolls = rollDice(dice, numSides, numRolls);

        //Find max frequency from rolls
        max = findMax(rolls);

        //Report or print roll totals
        report(numDice, rolls, max);
    }

    /**
     * Asks the user to enter 3 whole numbers, the number of dice, the number
     * of sides, and the number of rolls, and returns that as an array.
     * @return a 3 integer array from user input.
     */
    private static int[] getInput() {

        //Variables
        Scanner scan = new Scanner(System.in);
        int[] intArray = new int[3];
        boolean isValid = false;

        //Gather valid user input
        while (!isValid) {
            //Get user input
            System.out.println("""
                    Please enter the number of dice to roll, how many sides the dice have,
                    and how many rolls to complete, separating the values by a space.
                    Example: "2 6 1000"\s""");
            System.out.print("\nEnter configuration: ");
            String userInput = scan.nextLine();
            String[] strArray = userInput.split(" ");
            //Check validity of user input
            try {
                if (strArray.length != 3) {
                    throw new IllegalArgumentException("Invalid input: " +
                            "Expected 3 values but received " + strArray.length);
                }
                for (int i = 0; i < intArray.length; i++) {
                    intArray[i] = Integer.parseInt(strArray[i]);
                }
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: All values must be whole numbers.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
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
        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    /**
     * Takes in an array of dice and rolls them a user specified number of times,
     * finding their total, and then counts the frequency of each roll total,
     * returning the frequency results as an integer array.
     *
     * @param dice is the array of die to be rolled.
     * @param numSides is the number of sides on each die.
     * @param numRolls is the number of times to roll the dice.
     * @return array containing frequency of rolls for each possible output.
     */
    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int[] totals = new int[(dice.length * numSides) - (dice.length - 1)];
        for (int currentRoll = 0; currentRoll < numRolls; currentRoll++) {
            int total = 0;
            for (Die die : dice) {
                die.roll();
                total += die.getCurrentValue();
            }
            totals[total - dice.length] += 1;
        }
        return totals;
    }

    /**
     * Takes in an integer array and returns the largest integer in the array.
     * @param rolls is the array of roll total frequency to be analyzed.
     * @return the largest integer in the array.
     */
    private static int findMax(int[] rolls) {
        int max = 0;
        for (int roll : rolls) {
            if (roll > max) {
                max = roll;
            }
        }
        return max;
    }

    /**
     * Displays the frequency of each possible total as well as
     * a star representation of each frequency's size rounded down to
     * the nearest 10% compared to the largest frequency.
     *
     * @param numDice is the number of dice rolled during this run.
     * @param rolls is the int array containing roll frequency information.
     * @param max is the maximum frequency of a rolled total.
     */
    private static void report(int numDice, int[] rolls, int max) {
        //Scale is how many rolls is 10% of the max
        final int scaleconstant = 10;
        int scale = max / scaleconstant;
        int numStars;
        for (int i = 0; i < rolls.length; i++) {
            try {
                //Number of asterisks for a value is the number of rolls divided by the scale
                numStars = rolls[i] / scale;
            } catch (ArithmeticException e) {
                numStars = 0;
            }
            System.out.printf("%-2d%s%-9d%-9s%n", i+numDice, ":", rolls[i], "*".repeat(numStars));
        }
    }
}