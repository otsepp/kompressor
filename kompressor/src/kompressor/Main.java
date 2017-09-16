
package kompressor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String s = "bananabandana";
        System.out.print(s + " -> ");
        List<Integer> encoded = encode(s);
        System.out.println(encoded + " -> " + (decode(encoded)));
    }
    
    public static List<Integer> encode(String s) {
        Map<String, Integer> dictionary = initialiseEncodingDictionary();
        int code = 25; 
        
        List<Integer> encoded = new ArrayList();
        
        String b = "";
        String a = "";
        
        for (int i = 0; i < s.length(); i++) {
            b = s.charAt(i) + "";
            if (dictionary.containsKey(a + b)) {
                a = a + b;
            } else {
                encoded.add(dictionary.get(a));
                dictionary.put(a + b, ++code);
                a = b;
            }
        }
        encoded.add(dictionary.get(a));
        return encoded;
    }
    
    public static String decode(List<Integer> encoded) {
        Map<Integer, String> dictionary = initialiseDecodingDictionary();
        
        int maxCode = 25;
        
        String decoded = "";
        String prev = "";
        
        for (int code : encoded) {
            String current = dictionary.get(code);
            decoded  = decoded.concat(current);
            String concat = prev + current.charAt(0);
            
            if (!dictionary.containsValue(concat)) {
                 dictionary.put(++maxCode, concat);
             }
             prev = current;
        }
        return decoded;
    }

     //a-z
    public static Map<String, Integer> initialiseEncodingDictionary() {
         Map<String, Integer> dictionary = new HashMap();
        for (int i = 0, j = 97; i <= 25; i++, j++) {
//            System.out.println((char) j + " = " + j + " -> " + i);
            dictionary.put((char) j + "", i);
        }
        return dictionary;
    }
    
    //a-z
    public static Map<Integer, String> initialiseDecodingDictionary() {
        Map<Integer, String> dictionary = new HashMap();
        for (int i = 0; i <= 25; i++) {
            dictionary.put(i, (char)(i+97) + "");
        }
        return dictionary;
    }
   
    
}
