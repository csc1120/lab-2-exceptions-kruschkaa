/**
 * Course: CSC1020 - 121
 * Fall 2024
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Alexander Kruschka
 * Last Updated: 9/11/2024
 */

package kruschkaa;

/**
 * This class is a custom exception that is thrown when
 * the Die class detects that the dice hasn't been rolled
 * before calling for its current value.
 */
public class DieNotRolledException extends RuntimeException {

    public String getMessage() {
        return "Error, die has not been rolled and outside of possible range.";
    }

}
