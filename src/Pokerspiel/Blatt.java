package Pokerspiel;

import java.security.InvalidParameterException;

public class Blatt {

    private int[] kartenwerte = new int[3];

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

    public int[] getKartenwerte() {
        int[] returnable = {this.kartenwerte[0], this.kartenwerte[1], this.kartenwerte[2]};
        return returnable;
    }

    public int getSumOfCards() {
        return this.kartenwerte[0] + this.kartenwerte[1] + this.kartenwerte[2];
    }

    @Override
    public String toString() {
        return "" + kartenwerte[0] + ", " + kartenwerte[1] + ", " + kartenwerte[2];
    }
}
