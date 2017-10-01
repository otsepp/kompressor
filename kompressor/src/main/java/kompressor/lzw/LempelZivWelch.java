
package kompressor.lzw;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LempelZivWelch {
    
    private static final int INIT_DICT_SIZE = 256;
    private static final int CODE_WIDTH = 12;

    private LempelZivWelch() {
    }
    
    public static List<Integer> encode(String s) {
        Map<String, Integer> dictionary = initialiseEncodingDictionary();
        int nextCode = INIT_DICT_SIZE; 
        String prev = "";
        List<Integer> encoded = new ArrayList();
        
        for (int i = 0; i < s.length(); i++) {
            String current = s.charAt(i) + "";
            
            if (dictionary.containsKey(prev + current)) {
                prev = prev + current;
            } else {
                encoded.add(dictionary.get(prev));
                dictionary.put(prev + current, nextCode++);
                prev = current;
            }
        }
        if (!prev.equals("")) {
            encoded.add(dictionary.get(prev));
        }
        return encoded;
    }
    
   
    
    public static String decode(List<Integer> encoded) {
        Map<Integer, String> dictionary = initialiseDecodingDictionary();
        int nextCode = INIT_DICT_SIZE;
        StringBuilder decoded = new StringBuilder();
        String prev = "";
        
        for (int code : encoded) {
            String current;
            
            if (dictionary.containsKey(code)) {
                current = dictionary.get(code);
            } else {
                current = prev + prev.charAt(0);
            }
            decoded.append(current);
            
            if (!prev.equals("")) {
                String concat = prev + current.charAt(0);
                dictionary.put(nextCode++, concat);
            }
            prev = current;
        }
        return decoded.toString();
    }

    private static Map<String, Integer> initialiseEncodingDictionary() {
         Map<String, Integer> dictionary = new HashMap();
         for (int i = 0; i < INIT_DICT_SIZE; i++) {
             dictionary.put((char) i + "", i);
         }
        return dictionary;
    }
    
    private static Map<Integer, String> initialiseDecodingDictionary() {
        Map<Integer, String> dictionary = new HashMap();
        for (int i = 0; i < INIT_DICT_SIZE; i++) {
            dictionary.put(i, (char) i + "");
        }
        return dictionary;
    }
    
}
