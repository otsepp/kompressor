package kompressor.lzw.structures;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class lzwDictionaryTest {
    
    public lzwDictionaryTest() {
    }

    @Test
    public void putAndGetTest() {
        lzwDictionary<Integer, String> d1 = new lzwDictionary();
        Map<Integer, String> d2 = new HashMap();
        
        for (int i = 0; i < 1000; i++) {
             d1.put(i, (char) i + "");
             d2.put(i, (char) i + "");
             
             assertEquals(d1.get(i).equals(d2.get(i)), true);
        }
    }
    
}
