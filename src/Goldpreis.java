import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Goldpreis {


    private ArrayList<Goldtagespreis> list;

    public Goldpreis (String dateiname) throws IOException {
        this.list = new ArrayList<>();
        String[] splitLine;
        List<String> lines = Files.readAllLines(Path.of(dateiname));
        for (String line : lines) {
            splitLine = line.split("  ");
            System.out.println(splitLine[0] + " " + splitLine[1]);
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

    public double getPreis(String datum) {
        int index = -1;
        try {
            index = findIndexOfDate(datum);
        } catch (NoSuchElementException e) {
            System.out.println("There is no data for this date yet");
        }

        return this.list.get(index).preis;

    }

    private int findIndexOfDate(String datum) {
        for (int i = 0; i < this.list.size(); i++) {
            if(this.list.get(i).datum.equals(datum)) {
                return i;
            }
        }
        throw new NoSuchElementException(); //in case date doesn't exist
    }

    public void printMinMax() {
        double[] minMax = getMinMax();
        System.out.printf("Den niedrigsten Goldpreis von %2f gab es an folgenden Tagen:\n", minMax[0]);
        System.out.println(getDatesByPrice(minMax[0]));
        System.out.printf("Den hoechsten Goldpreis von %2f gab es an folgenden Tagen:\n", minMax[1]);
        System.out.println(getDatesByPrice(minMax[1]));

    }

    private ArrayList<String> getDatesByPrice(double price) {
        ArrayList<String> results = new ArrayList<>();
        for (Goldtagespreis element : list) {
            if(element.preis == price) {
                results.add(element.datum);
            }
        }
        return results;
    }

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
