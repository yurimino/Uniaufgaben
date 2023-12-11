package Pokerspiel;

import java.security.InvalidParameterException;


/**
 *  the Blatt class represents a hand of 3 cards with values between 2 and 14
 */
public class Blatt {

    /**
     * the 3 cards of the current hand
     */
    private int[] kartenwerte = new int[3];

    /**
     * contructor for initializing the current hand of cards, checks for validity of the array (no more than 3 cards) and the range of the card values (2-14)
     * @param kartenwerte initial card values
     * @throws InvalidParameterException if the array is of a length other than 3 or the entries of the given array are out of range for the cards
     *
     */
    public Blatt(int[] kartenwerte) {
        if(kartenwerte.length != 3) {
            throw new InvalidParameterException();
        } else {
            for (int i = 0; i < 3; i++) {
                if(kartenwerte[i] < 2 || kartenwerte[i] > 14) {
                    throw new InvalidParameterException();
                }
                // fuer Datenkapselung
                this.kartenwerte[i] = kartenwerte[i];
            }
        }
    }

    //getter
    public int[] getKartenwerte() {
        int[] returnable = {this.kartenwerte[0], this.kartenwerte[1], this.kartenwerte[2]};
        return returnable;
    }

    /**
     * adds up the values of all cards and returns said sum
     * @return int value of the sum of the card values
     */
    public int getSumOfCards() {
        return this.kartenwerte[0] + this.kartenwerte[1] + this.kartenwerte[2];
    }

    /**
     * turns the card set into a human-readable string
     * @return a String containing the card set in the form "[value1], [value2], [value3]"
     */
    @Override
    public String toString() {
        return "" + kartenwerte[0] + ", " + kartenwerte[1] + ", " + kartenwerte[2];
    }
}
