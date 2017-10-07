
package kompressor.lzw;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import kompressor.lzw.structures.lzwDictionary;

public class LempelZivWelch {
    
    private static final int INIT_DICT_SIZE = 256;
    private static final int MAX_DICT_SIZE = 4096;

    private LempelZivWelch() {
    }
    
   public static byte[] encode(byte[] bytes) throws IOException {
       ByteArrayOutputStream bs = new ByteArrayOutputStream();
       lzwDictionary<String, Integer> dictionary = initialiseEncodingDictionary();
       int nextCode = INIT_DICT_SIZE;
       String prev = "";
       
       for (byte b : bytes) {
           String current = (char) b + "";
           
            if (dictionary.containsKey(prev + current)) {
                prev = prev + current;
            } else {
                bs.write(create12BitCode(dictionary.get(prev)));
                dictionary.put(prev + current, nextCode++);
                
                if (nextCode == MAX_DICT_SIZE) {
                    dictionary = initialiseEncodingDictionary(); 
                    nextCode = INIT_DICT_SIZE;
                }
                prev = current;
            }
       }
       if (!prev.equals("")) {
           bs.write(create12BitCode(dictionary.get(prev)));
        }
       return bs.toByteArray();
   }
   
    public static byte[] decode(byte[] bytes) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        
        lzwDictionary<Integer, String> dictionary = initialiseDecodingDictionary();
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
                dictionary.put(nextCode++, concat);
                
                 if (nextCode == MAX_DICT_SIZE) {
                    dictionary = initialiseDecodingDictionary(); 
                    nextCode = INIT_DICT_SIZE;
                }
            }
            prev = current;
            i+=2;
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
    
    private static lzwDictionary<String, Integer> initialiseEncodingDictionary() {
         lzwDictionary<String, Integer> dictionary = new lzwDictionary();
         for (int i = 0; i < INIT_DICT_SIZE; i++) {
             dictionary.put((char) i + "", i);
         }
        return dictionary;
    }
    
    private static lzwDictionary<Integer, String> initialiseDecodingDictionary() {
        lzwDictionary<Integer, String> dictionary = new lzwDictionary();
        for (int i = 0; i < INIT_DICT_SIZE; i++) {
            dictionary.put(i, (char) i + "");
        }
        return dictionary;
    }
    
}
