
package kompressor;

import kompressor.lzw.LempelZivWelch;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        System.out.println("***Lempel-Ziv-Welch***");
        System.out.print("Syötä merkkijono: \n >");
        
        String input = s.nextLine();
        System.out.println("Merkkijonon pituus on " + input.length());
        
        List<Integer> encoded = LempelZivWelch.encode(input);
        System.out.println("Saadaan " + encoded);
        System.out.println("Pituus tiivistettynä on: " + encoded.size());
        
        System.out.println("Purkamistesti: " + input.equals(LempelZivWelch.decode(encoded)));
    }
    
}
