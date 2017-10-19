
package kompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import kompressor.huffman.Huffman;
import kompressor.lzw.LempelZivWelch;

public class Main {

    public static void main(String[] args) throws IOException {
        lzw();
    }
    
    //nopeasti tehty copy paste metodi
    public static void huffman() {
         try {
            File f = new File("src/resources/textverylong.txt");
            FileInputStream in = new FileInputStream(f);

            byte[] bytes1 = new byte[(int)f.length()];
            int c;
            int i = 0;
            while ((c = in.read()) != -1) {
                bytes1[i++] = (byte) c;
            }
            System.out.println("Luettiin tekstitiedosto sijainnista " + f.getPath() + ", koko: " + bytes1.length + " tavua");
            
            byte[] bytes2 = Huffman.encode(bytes1);
            f = new File("src/resources/compressed");
            FileOutputStream out = new FileOutputStream(f);
            out.write(bytes2);
            
            System.out.println("Tiedosto pakattiin sijaintiin " + f.getPath() + ", koko: " + bytes2.length + " tavua");
            System.out.printf("Koko alkuperäisestä: %.2f \n", (double) bytes2.length / bytes1.length);
            
            in = new FileInputStream(f);
            bytes2 = Huffman.decode(bytes2);
            
            System.out.println("Purkamistesti: " + Arrays.equals(bytes1, bytes2));
            
            in.close();
            out.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    public static void lzw() {
        try {
            File f = new File("src/resources/textverylong.txt");
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
    
}
