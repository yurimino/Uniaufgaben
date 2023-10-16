
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * represents the price of gold on a specific date
 */
public class Goldtagespreis {

    //may be public

    /**
     * String value of the date in the format "yyyy-MM-dd"
     */
    String datum;
    /**
     * price as a positive double value S
     */
    double preis;

    /**
     * constructor that takes the date and price and checks them for validity before assigning them to the attributes
     * @param datum String value containing the date in the format "yyyy-MM-dd"
     * @param preis price as a positive double value
     * @throws IllegalArgumentException if an invalid date has been found or the price is invalid
     */
    public Goldtagespreis(String datum, double preis) {

        if(!datumIsValid(datum) || (preis < 0 && preis != -1)) { //-1 must be allowed to indicate that there was no price data for the given date
            throw new IllegalArgumentException();
        }
        this.datum = datum;
        this.preis = preis;

    }

    /**
     * override of the toString() method
     * @return String of the format "Datum: [datum]; Preis: [preis]"
     */
    @Override
    public String toString() {
        return "Datum: " + this.datum + "; Preis: " + preis;
    }

    /**
     * checks if a given date is of the valid format (yyyy-MM-dd) and consists of numbers that are valid for the given date format
     * @param datum String value containing the date to be checked
     * @return true if the date is valid, else false
     *
     */
    private boolean datumIsValid(String datum){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //declares the format that is being used
            format.setLenient(false); //ensures that only dates that really exist return true, even if the non-existent date has the correct format
            format.parse(datum); //doesn't need to be saved anywhere because it is just supposed to throw an exception if parsing isn't possible
            return true; //never reached if parsing throws exception
        } catch (ParseException e){
            System.out.println(datum + " is not valid. It's either in the wrong format or the date does not exist");
            return false;
        }
    }
}
