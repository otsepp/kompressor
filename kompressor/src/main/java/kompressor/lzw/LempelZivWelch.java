
package kompressor.lzw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LempelZivWelch {
    
    private static final int INIT_DICT_SIZE = 256;
    private static final int MAX_DICT_SIZE = 4095;
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
    
   public static byte[] encodeBytes(byte[] bytes) throws IOException {
       ByteArrayOutputStream bs = new ByteArrayOutputStream();
       Map<String, Integer> dictionary = initialiseEncodingDictionary();
       int nextCode = INIT_DICT_SIZE;
       String prev = "";
       
       for (byte b : bytes) {
           String current = (char) b + "";
           
            if (dictionary.containsKey(prev + current)) {
                prev = prev + current;
            } else {
                bs.write(create12BitCode(dictionary.get(prev)));
                dictionary.put(prev + current, nextCode++);
                
                //implement!!!!!!!
                if (nextCode == MAX_DICT_SIZE) {
                    System.out.println("encoding dictionary full!!");
//                    dictionary = initialiseEncodingDictionary();
                }
                prev = current;
            }
       }
       if (!prev.equals("")) {
           bs.write(create12BitCode(dictionary.get(prev)));
        }
       return bs.toByteArray();
   }
    private static byte[] create12BitCode(int code) {
        byte[] b = new byte[2];
        if (Integer.toBinaryString(code).length() > 8) {
            b[0] = (byte) ((code & 0xF00) >> 8);
        } else {
            b[0] = 0x0;
        }
        b[1] = (byte) code;
        return b;
    }
   
    public static byte[] decodeBytes(byte[] bytes) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        
        Map<Integer, String> dictionary = initialiseDecodingDictionary();
        int nextCode = INIT_DICT_SIZE;
        String prev = "";
        
        for (int i = 0; i < bytes.length;) {
            ByteBuffer codeBuffer = ByteBuffer.wrap(new byte[]{ 0x0, 0x0, bytes[i], bytes[i+1] });
            int code = codeBuffer.getInt();
            String current;
            
            if (dictionary.containsKey(code)) {
                current = dictionary.get(code);
            } else {
                current = prev + prev.charAt(0);
            }
            for (char c : current.toCharArray()) {
                bs.write(c);
            }
            
            if (!prev.equals("")) {
                String concat = prev + current.charAt(0);
                
                //implement!!!!!!!
                if (dictionary.size() == MAX_DICT_SIZE) {
                    System.out.println("decoding dictionary full!!");
//                    dictionary = initialiseDecodingDictionary();
                }
                dictionary.put(nextCode++, concat);
            }
            prev = current;
            i+=2;
        }
        return bs.toByteArray();
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
