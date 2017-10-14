
package kompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import kompressor.huffman.ByteArrayWriter;
import kompressor.huffman.Huffman;
import kompressor.huffman.HuffmanDemo;
import kompressor.huffman.HuffmanTree;
import kompressor.lzw.LempelZivWelch;

public class Main {

    public static void main(String[] args) throws IOException {
          //JUNIT TEST
//        ByteArrayWriter bwr = new ByteArrayWriter();
//        bwr.writeZero();
//        bwr.writeOne();
//        bwr.writeCharacter('e');
//        bwr.writeOne();
//        bwr.writeCharacter('i');
//        bwr.writeZero();
//        bwr.writeZero();
//        bwr.writeOne();
//        bwr.writeCharacter('b');
//        bwr.writeOne();
//        bwr.writeCharacter('c');
//        bwr.writeOne();
//        bwr.writeCharacter('o');
//        for (byte b : bwr.toByteArray()) {
//            System.out.println(b);
//        }

        String s = "cbboooiiiieeeee";
        for (byte b : Huffman.encode(s.getBytes())) {
            System.out.println(b);
        }
        
//        for (byte b : code.toByteArray()) {
//            System.out.println(Integer.toBinaryString(b));
//        }
        
//        lzw();
//        huffman(new Scanner(System.in));
    }
    
    public static void lzw() {
        try {
            File f = new File("src/resources/textlong.txt");
            FileInputStream in = new FileInputStream(f);

            byte[] bytes1 = new byte[(int)f.length()];
            int c;
            int i = 0;
            while ((c = in.read()) != -1) {
                bytes1[i++] = (byte) c;
            }
            System.out.println("Luettiin tekstitiedosto sijainnista " + f.getPath() + ", koko: " + bytes1.length + " tavua");
            
            byte[] bytes2 = LempelZivWelch.encode(bytes1);
            f = new File("src/resources/compressed");
            FileOutputStream out = new FileOutputStream(f);
            out.write(bytes2);
            
            System.out.println("Tiedosto pakattiin sijaintiin " + f.getPath() + ", koko: " + bytes2.length + " tavua");
            System.out.printf("Koko alkuperäisestä: %.2f \n", (double) bytes2.length / bytes1.length);
            
            in = new FileInputStream(f);
            bytes2 = LempelZivWelch.decode(bytes2);
            
            System.out.println("Purkamistesti: " + Arrays.equals(bytes1, bytes2));
            
            in.close();
            out.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    public static void huffman(Scanner s) {
        System.out.print("\n***Huffman***\n"
                + "Syötä merkkijono: \n>");
        
        String input = s.nextLine();
        System.out.println("Merkkijonon pituus on " + input.length());
        
        HuffmanDemo h = new HuffmanDemo(input);
        String encoded = h.encode();
        System.out.println("Saadaan: \n" + encoded
                + "\nPituus tiivistettynä: "+ encoded.length() + " \"bittiä\"");
        
        System.out.println("Purkamistesti: " + input.equals(h.decode()));
    }
    
}
