
package kompressor;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println(encode("zululzu"));
    }
    
    public static String encode(String a) {
        Map<String, Integer> dictionary = initialiseEncodingDictionary();
        int code = 25; //get by final field!!!
        
        String encoded = "";
        
        String s = "";
        String ch = "";
        
        for (int i = 0; i < a.length(); i++) {
            ch = a.charAt(i) + "";
            if (dictionary.containsKey(s + ch)) {
                s = s + ch;
            } else {
                encoded = encoded.concat(dictionary.get(s) + " ");
                dictionary.put(s + ch, ++code);
                s = ch;
            }
        }
        encoded = encoded.concat(dictionary.get(s) + " ");
        return encoded;
    }
    
    public static Map<String, Integer> initialiseEncodingDictionary() {
         Map<String, Integer> dictionary = new HashMap();
        //a - z
        for (int i = 0, j = 97; i < 26; i++, j++) {
            System.out.println((char) j + " = " + j + " -> " + i);
            dictionary.put((char) j + "", i);
        }
        return dictionary;
    }
    
}
