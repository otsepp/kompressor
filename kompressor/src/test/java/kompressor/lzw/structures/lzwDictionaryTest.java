package kompressor.lzw.structures;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class lzwDictionaryTest {
     private lzwDictionary<String, Integer> dict;
    
    public lzwDictionaryTest() {
    }
    
    @Before
    public void setUp() {
        dict = new lzwDictionary();
    }
    
    @Test
    public void putAndGetTest() {
        for (int i = 0; i < 256; i++) {
            dict.put((char) i + "", i);
            assertEquals(i, dict.get((char) i + "").intValue());
        }
    }
    
    @Test
    public void addCollisionTest() {
        String s1 = "FB";
        String s2 = "Ea";
        dict.put("FB", 1);
        dict.put("Ea", 2);
        assertEquals(1, dict.get("FB").intValue());
        assertEquals(2, dict.get("Ea").intValue());
    }
    
}
