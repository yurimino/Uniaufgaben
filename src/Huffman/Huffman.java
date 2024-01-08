  package Huffman;

  import java.io.BufferedReader;
  import java.io.File;
  import java.io.FileReader;
  import java.io.IOException;
  import java.sql.SQLOutput;
  import java.util.HashMap;

  public class Huffman {

      public static void main(String[] args) {
          System.out.println(decode(new File("src/Huffman/message.txt"))); //fix later due to weird path mumbo-jumbo
      }

      //Grossbuchstaben und Lehrzeichen => 65 bis 90 und 32


    public static String decode(File f) {
        StringBuilder plaintext = new StringBuilder();
        BufferedReader reader;
        String text;
        HashMap<String, Character> codes;

        try {

            reader = new BufferedReader(new FileReader(f));

            text = reader.readLine();

            reader.close();
            codes = getTable(f);

            int start = 0;
            int end = 1;

            while(end <= text.length()) {
                if(codes.containsKey(text.substring(start,end))) {
                    plaintext.append(codes.get(text.substring(start,end)));
                    start = end;
                    end = start + 1;
                } else {
                    end++;
                }
            }

            return plaintext.toString();

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }




        //28 Zeilen
        //1. Zeile: verschluesselter Text
        //2-27 codierung a bis z
        //28 codierung leerzeichen
        //leerzeile = kommt nicht vor

    }

    private static HashMap<String, Character> getTable (File f) {
        try {
            HashMap<String, Character> codes = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();

            for (char c = 'A'; c <= 'Z'; c++) {
                line = reader.readLine();
                if (!line.isEmpty()) {
                    codes.put(line, c);
                }
            }
            line = reader.readLine();
            if (!line.isEmpty()) {
                codes.put(line, ' ');
            }
            reader.close();
            return codes;

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

    }
}
