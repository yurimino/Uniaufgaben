import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class EmailAdressBuch {

     private HashMap<String, String> adressBuch;

     public EmailAdressBuch() {
         this.adressBuch = new HashMap<>();
     }

     public void einfuegen(String name, String email) {
         this.adressBuch.put(name, email);
     }

     public void einlesen(String dateiname) {
         try {
             List<String> lines = Files.readAllLines(Path.of(dateiname));
             String[] nameEmailPair = null;
             for(String line : lines) {
                 nameEmailPair = line.split(";");
                 this.adressBuch.put(nameEmailPair[0], nameEmailPair[1]);
             }
         } catch (IOException e) {
             System.out.println(e.getMessage());

         }

     }

     public String abfrage(String name) {
        String email = this.adressBuch.get(name);
        if(email == null) {
            throw new UnknownNameException("This name doesn't exist");

        }
        return email;
     }

     @Override
     public String toString() {
        return this.adressBuch.toString();
     }

}
