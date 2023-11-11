package Pokerspiel;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class BlattVergleich implements Comparator<Blatt> {
    /**
     * compares two hands of cards
     * @param b1 the first object to be compared.
     * @param b2 the second object to be compared.
     * @return value < 0 if b1<b2, value > 0 if b1>b2, 0 if b1==b2
     */
    @Override
    public int compare(Blatt b1, Blatt b2) {
        int[] blatt1 = sameNumberAmount(b1.getKartenwerte());
        int[] blatt2 = sameNumberAmount(b2.getKartenwerte());

        int biggerTuple = blatt1[0] - blatt2[0];
        int biggerValue = blatt1[1] - blatt2[1];
        if(biggerTuple != 0) { //tupel unterscheidbar
            return biggerTuple;
        } else if (blatt1[0] !=2 || (blatt1[0] == 2 && biggerValue !=0)) { //gleiches Tupel aber Wert unterscheidbar
            return biggerValue;
        } else {
            int otherValueB1 = b1.getSumOfCards() - 2 * blatt1[1];
            int otherValueB2 = b2.getSumOfCards() - 2 * blatt2[1];
            return otherValueB1 - otherValueB2; //3. karte zaehlt
        }


    }

    /**
     * sees how often the same number appears in an array of the size 3
     * @param arr the array to be analyzed
     * @return array with the amount of times the same number has appeared and the value of said number, or the sum of all three numbers if all have been different
     */
    private int[] sameNumberAmount(int[] arr) {
        if(arr.length != 3 ) {
            throw new InvalidParameterException();
        }
        int[] result = new int[2];
        if(arr[0] == arr[1] && arr[1] == arr[2]) {
            result[1] = arr[0];
            result[0] = 3;
        } else if (arr[0] != arr[1] && arr[1] != arr[2] && arr[0] != arr[2]){
            result[1] = arr[0] + arr[1] + arr[2];
            result[0] = 1;
        } else {
            result[0] = 2;
            if(arr[0] == arr[1] || arr[0] == arr[2]) {
                result[1] = arr[0];

            } else {
                result[1] = arr[1];

            }
        }


        return result;
    }
}
