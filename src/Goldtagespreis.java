
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goldtagespreis {

    //may be public
    String datum;
    double preis;

    public Goldtagespreis(String datum, double preis) {

        if(!datumIsValid(datum) || (preis < 0 && preis != -1)) { //-1 must be allowed to indicate that there was no price data for the given date
            throw new IllegalArgumentException();
        }
        this.datum = datum;
        this.preis = preis;

    }

    @Override
    public String toString() {
        return "Datum: " + this.datum + " ; Preis: " + preis;
    }

    private boolean datumIsValid(String datum){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd"); //declares the format that is being used
            format.setLenient(false); //ensures that only dates that really exist return true, even if the non-existent date has the correct format
            Date parsedDate = format.parse(datum);
            return true; //never reached if parsing throws exception
        } catch (ParseException e){
            System.out.println(datum + " is not valid. It's either in the wrong format or the date does not exist");
            return false;
        }
    }
}
