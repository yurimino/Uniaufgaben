
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * represents a collection of gold price values over time
 */
public class Goldpreis {

    /**
     * ArrayList containing Goldtagespreis objects
     */
    private final ArrayList<Goldtagespreis> list;

    /**
     * constructor which reads date-price pairs from a file at a specified path, makes Goldtagespreis objects out of them and adds those objects to the list attribute
     * @param dateiname String value of the relative path of the file
     * @throws IOException from Files.readAllLines if path name is invalid
     */
    public Goldpreis (String dateiname) throws IOException {
        this.list = new ArrayList<>();
        String[] splitLine;
        List<String> lines = Files.readAllLines(Path.of(dateiname));
        for (String line : lines) {
            splitLine = line.split(" {2}");
            double price = 0;
            try{
                price = Double.parseDouble(splitLine[1]);
                //catches the case that the day was a weekend or there is some mistake in the format
            } catch(NumberFormatException e) {
                if(splitLine[1].equals("kein Nachweis")) {
                    price = -1.;
                } else {
                    System.out.println("Price data invalid");
                    System.exit(1);
                }
            }
            this.list.add(new Goldtagespreis(splitLine[0], price));
        }


    }

    /**
     * get the price of gold on the specified date
     * @param datum date of the requested gold value
     * @return value of gold as a double value on the given date
     */
    public double getPreis(String datum) {
        int index = -1;
        try {
            index = findIndexOfDate(datum);
        } catch (NoSuchElementException e) {
            System.out.println("There is no data for this date yet");
        }

        return this.list.get(index).preis;

    }

    /**
     * support method for getPreis() which returns the first index at which a given date can be found
     * @param datum String value containing the date which is being searched for
     * @return int value of the index of the requested date
     * @throws NoSuchElementException if the requested date does not exist in the list
     */
    private int findIndexOfDate(String datum) {
        for (int i = 0; i < this.list.size(); i++) {
            if(this.list.get(i).datum.equals(datum)) {
                return i;
            }
        }
        throw new NoSuchElementException(); //in case date doesn't exist
    }

    /**
     * prints the minimum and maximum gold values and every date on which they occurred
     */
    public void printMinMax() {
        double[] minMax = getMinMax();
        System.out.printf("Den niedrigsten Goldpreis von %2f gab es an folgenden Tagen:\n", minMax[0]);
        System.out.println(getDatesByPrice(minMax[0]));
        System.out.printf("Den hoechsten Goldpreis von %2f gab es an folgenden Tagen:\n", minMax[1]);
        System.out.println(getDatesByPrice(minMax[1]));

    }

    /**
     * support method for printMinMax() to get a list of all dates on which gold had a specific price
     * @param price the price being filtered by as a double value
     * @return ArrayList of Strings containing the dates on which gold had the price in question
     */
    private ArrayList<String> getDatesByPrice(double price) {
        ArrayList<String> results = new ArrayList<>();
        for (Goldtagespreis element : list) {
            if(element.preis == price) {
                results.add(element.datum);
            }
        }
        return results;
    }

    /**
     * support method for printMinMax() which determines the minimum and maximum price of gold in the list attribute
     * @return array of two double values representing the minimum and maximum in the form [min, max]
     */
    private double[] getMinMax() {
        double[] minMax = {this.list.get(0).preis,this.list.get(0).preis}; //minMax[0] is min, minMax[1] is max
        for(Goldtagespreis element : list) {
            if(element.preis > minMax[1]) {
                minMax[1] = element.preis;
            } else if (element.preis != -1 && element.preis < minMax[0]){
                minMax[0] = element.preis;
            }
        }
        return minMax;
    }
}
