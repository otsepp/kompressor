
package testingMethods;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class TestingMethods {
    
    private TestingMethods() { 
    }
    
    //ei muokka sanakirjaa!!
    public static String decodingTest(String encoded) {
        //sanakirja init, a-z
        int[] codesDecoding = new int[26];
        for (int i = 0, val = 97; i < 26; i++, val++) {
            System.out.println(i + " -> " + val + " = " + (char) val);
            codesDecoding[i] = val;
        }
        String decoded = "";
        
        for (int i = 0; i < encoded.length(); i++) {
            String current = encoded.charAt(i) + "";
            String code = "";
            
            while (!current.equals(" ") && i < encoded.length()) {
                code = code.concat(current);
                if (i < encoded.length() - 1) {
                    i++;
                    current = encoded.charAt(i) + "";
                } else {
                    break;
                }
           }
            decoded = decoded.concat((char) codesDecoding[new Integer(code)] + "");
        }
        return decoded;
    }
    
    public static String readInputTest() {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Syötä merkkijono joka koostuu kirjaimista a - z :");
        System.out.print("\t>");
        
        String input = s.nextLine();
        
        if (input.matches("[a-z]+")) {
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
