
package kompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Scanner;
import kompressor.huffman.Huffman;
import kompressor.lzw.LempelZivWelch;

public class Main {

    public static void main(String[] args) {
        try {
            File f = new File("src/resources/textlong.txt");
            FileInputStream in = new FileInputStream(f);

            byte[] bytes = new byte[(int)f.length()];
            int c;
            int i = 0;
            while ((c = in.read()) != -1) {
                bytes[i++] = (byte) c;
            }
            int size1 = bytes.length;
            System.out.println("Luettiin tekstitiedosto sijainnista " + f.getPath() + ", koko: " + size1 + " tavua");
            
            bytes = LempelZivWelch.encodeBytes(bytes);
            int size2 = bytes.length;
            
            f = new File("src/resources/compressed");
            FileOutputStream out = new FileOutputStream(f);
            out.write(bytes);
            
            System.out.println("Tiedosto pakattiin sijaintiin " + f.getPath() + ", koko: " + size2 + " tavua");
            System.out.printf("Koko alkuperäisestä: %.2f prosenttia", (double) size2 / size1);
            
            in.close();
            out.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    public static void demonstration() {
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
