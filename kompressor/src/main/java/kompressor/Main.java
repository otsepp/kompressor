package kompressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import kompressor.huffman.Huffman;
import kompressor.lzw.LempelZivWelch;

public class Main {

    public static void main(String[] args) throws IOException  {
        boolean lzw = false;
        Scanner s = new Scanner(System.in);
        
       if (chooseAlg(s).equals("1")) lzw = true;
        
        File f = chooseFile(s);
        
        byte[] bytes1 = readFile(f);
        System.out.println("Luettiin tekstitiedosto sijainnista " + f.getPath() + ", koko: " + bytes1.length + " tavua");
        
        byte[] bytes2;
        
        if (lzw) bytes2 = LempelZivWelch.encode(bytes1);
        else bytes2 = Huffman.encode(bytes1);
        
        f = new File("src/resources/.compressed");
        FileOutputStream out = new FileOutputStream(f);
        out.write(bytes2);
        System.out.println("Tiedosto pakattiin sijaintiin " + f.getPath() + ", koko: " + bytes2.length + " tavua");
        System.out.printf("Koko alkuperäisestä: %.2f \n", (double) bytes2.length / bytes1.length);
        
        if (lzw) bytes2 = LempelZivWelch.decode(bytes2);
        else bytes2 = Huffman.decode(bytes2);
        System.out.println("Purkamistesti: " + Arrays.equals(bytes1, bytes2));
       
        out.close();
    }
    
    public static byte[] readFile(File f) throws FileNotFoundException {
        try {
            FileInputStream in = new FileInputStream(f);

            byte[] bytes1 = new byte[(int)f.length()];
            int c;
            int i = 0;
            while ((c = in.read()) != -1) {
                bytes1[i++] = (byte) c;
            }
            return bytes1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static File chooseFile(Scanner s) throws IOException {
        Path p = Paths.get("src/resources/");
        Map<Integer, File> m = new HashMap();
        
        int i = 1;
        for (Path pL : Files.newDirectoryStream(p)) {
            File f = pL.toFile();
            if (!f.getName().equals(".compressed")) m.put(i++, pL.toFile());
        }
        
        while (m.size() > 0) {
            System.out.println("\nValitse pakattava tiedosto: \n");
            
            for (Integer index : m.keySet()) {
                File f = m.get(index);
                System.out.println(index + ". " + f.getName() + ", " + f.length() + " tavua");
            }
            
            System.out.println("");
            
            int input;
            if (s.hasNextInt()) {
                input = s.nextInt();
                System.out.println("");
                if (m.containsKey(input)) return m.get(input);
            }
        }
        
        return null;
    }
    
    public static String chooseAlg(Scanner s) {
        while (true) {
            System.out.println("Valitse algoritmi: \n1. Lempel-Ziv-Welch \n2.Huffman");
            String alg = s.nextLine();
            if (alg.equals("1")) {
                return "1";
            }
            else if (alg.equals("2")) {
                return "2";
            }
            System.out.println("");
        }
    }
   
}
