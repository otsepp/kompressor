
package kompressor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static final int initalDictionarySize = 256;
    
    public static void main(String[] args) {
        String s = "TOBEORNOTTOBEORTOBEORNOT";
//        String s = "abababab";
        System.out.println(s + "(length: " + s.length() + ")");
        List<Integer> encoded = encode(s);
        System.out.println("-> " + encoded + " (length: " + encoded.size() + ")");

        String decoded = decode(encoded);
        System.out.println("-> " + decoded + ", " + s.equals(decoded));
    }
    
    public static List<Integer> encode(String s) {
        Map<String, Integer> dictionary = initialiseEncodingDictionary();
        int nextCode = initalDictionarySize + 1; 
        List<Integer> encoded = new ArrayList();
        String b = "";
        String a = "";
        
        for (int i = 0; i < s.length(); i++) {
            b = s.charAt(i) + "";
            
            if (dictionary.containsKey(a + b)) {
                a = a + b;
            } else {
                encoded.add(dictionary.get(a));
                dictionary.put(a + b, nextCode++);
                a = b;
            }
        }
        encoded.add(dictionary.get(a));
        return encoded;
    }
    
    public static String decode(List<Integer> encoded) {
        Map<Integer, String> dictionary = initialiseDecodingDictionary();
        int nextCode = initalDictionarySize + 1;
        String decoded = "";
        String prev = "";
        
        for (int code : encoded) {
            String current;
            
            if (dictionary.get(code) == null) {
                current = prev + prev.charAt(0);
            } else {
                current = dictionary.get(code);
            }
            decoded  = decoded.concat(current);
            
            if (!prev.equals("")) {
                String concat = prev + current.charAt(0);
                dictionary.put(nextCode++, concat);
            }
            prev = current;
        }
        return decoded;
    }

    public static Map<String, Integer> initialiseEncodingDictionary() {
         Map<String, Integer> dictionary = new HashMap();
         for (int i = 0; i < initalDictionarySize; i++) {
             dictionary.put((char) i + "", i);
         }
        return dictionary;
    }
    
    public static Map<Integer, String> initialiseDecodingDictionary() {
        Map<Integer, String> dictionary = new HashMap();
        for (int i = 0; i < initalDictionarySize; i++) {
            dictionary.put(i, (char) i + "");
        }
        return dictionary;
    }
   
    
}
