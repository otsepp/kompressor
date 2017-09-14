
package kompressor;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Integer i = new Integer(112);
//        String input = readInputTEST();
    }
    
    public static String readInputTEST() {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Syötä merkkijono joka koostuu kirjaimista a - c :");
        System.out.print("\t>");
        
        String input = s.nextLine();
        
        if (input.matches("[a-c]+")) {
            try {
                System.out.println("Merkkijonon \"" + input + "\" koko on " + input.getBytes("UTF-8").length * 8 +" bittiä.");
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Virheellinen syöte!");
        }
        return input;
    }
    
}
