
package kompressor;

import java.util.List;
import java.util.Scanner;
import kompressor.huffman.Huffman;
import kompressor.lzw.LempelZivWelch;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String ans = "";
        
        while (!ans.equals("1") && !ans.equals("2")) {
            System.out.print("Valitse algoritmi:\n"
                    + "1.: LZW\n" 
                    + "2.: Huffman\n"
                    + ">");
            ans = s.nextLine();
        }
        
        if (ans.equals("1")) {
            lzwTest(s);
        } else {
            huffmanTest(s);
        }
    }
    
    public static void lzwTest(Scanner s) {
        System.out.print("\n***Lempel-Ziv-Welch***\n"
            + "Syötä merkkijono: \n>");
        
        String input = s.nextLine();
        System.out.println("Merkkijonon pituus on " + input.length());
        
        List<Integer> encoded = LempelZivWelch.encode(input);
        System.out.println("Saadaan: " + encoded 
                + "\nPituus tiivistettynä on: " + encoded.size());
        
        System.out.println("Purkamistesti: " + input.equals(LempelZivWelch.decode(encoded)));
    }
  
    public static void huffmanTest(Scanner s) {
        System.out.print("\n***Huffman***\n"
                + "Syötä merkkijono: \n>");
        
        String input = s.nextLine();
        System.out.println("Merkkijonon pituus on " + input.length());
        
        Huffman h = new Huffman(input);
        String encoded = h.encode();
        System.out.println("Saadaan: \n" + encoded
                + "\nPituus tiivistettynä: "+ encoded.length() + " \"bittiä\"");
        
        System.out.println("Purkamistesti: " + input.equals(h.decode()));
    }
    
}
