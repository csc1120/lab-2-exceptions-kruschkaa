/**
 * Course: CSC1020 - 121
 * Fall 2024
 * Lab 2 - Exceptions
 * Die class
 * Name: Alexander Kruschka
 * Last Updated: 9/11/2024
 */

package kruschkaa;

import java.util.Random;

/**
 * This class creates a die with a number of sides
 * that can be rolled and return its current value.
 */
public class Die {

    //Data Field and Variables
    /**
     * Minimum sides a die may be initialized to.
     */
    public static final int MIN_SIDES = 2;
    /**
     * Maximum sides a die may be initialized to.
     */
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private Random random;

    //Constructor
    Die(int numSides) {
        if (numSides <= MAX_SIDES && numSides >= MIN_SIDES) {
            this.numSides = numSides;
        } else {
            throw new IllegalArgumentException("Bad die creation: " +
                    "Illegal number of sides: " + numSides);
        }
    }

    //Methods
    /**
     * Checks if current value is within range of die,
     * if yes, returns the value, else will throw exception.
     *
     * @return currentValue of die if value is legal.
     * @throws DieNotRolledException if current value is not legal.
     */
    public int getCurrentValue() {
        if (currentValue <= numSides && currentValue >= MIN_SIDES) {
            int tempValue = currentValue;
            currentValue = 0;
            return tempValue;
        } else {
            throw new DieNotRolledException();
        }
    }

    /**
     * Rolls the dice and sets currentValue to a random integer
     * within range of dice sides.
     */
    public void roll() {
        currentValue = random.nextInt(MIN_SIDES, numSides + 1);
    }
}