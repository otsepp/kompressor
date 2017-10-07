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
            assertEquals(dict.get((char) i + "") == i, true);
        }
    }
    
    @Test
    public void addCollisionTest() {
        String s1 = "FB";
        String s2 = "Ea";
        assertEquals(s1.hashCode() == s2.hashCode(), true);
        dict.put("FB", 1);
        dict.put("Ea", 2);
        assertEquals(dict.get("FB") == 1, true);
        assertEquals(dict.get("Ea") == 2, true);
    }
    
}
