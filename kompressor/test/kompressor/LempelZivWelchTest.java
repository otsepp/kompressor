/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kompressor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Glarthir
 */
public class LempelZivWelchTest {
    
    private String unencoded;
    private List<Integer> encoded;
    
    public LempelZivWelchTest() {
    }

    @Before
    public void initialise() {
        this.unencoded = "TOBEORNOTTOBEORTOBEORNOT";
        this.encoded = Arrays.asList(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263);
    }
    
    @Test
    public void testEncode() {
        List<Integer> encodedTest = LempelZivWelch.encode(unencoded);
        assertEquals(encodedTest, encoded);
    }

    @Test
    public void testEncodeEmptyString() {
        assertEquals(LempelZivWelch.encode(""), new ArrayList());
    }
    
    @Test
    public void testDecode() {
        String decodedTest = LempelZivWelch.decode(encoded);
        assertEquals(decodedTest, unencoded);
    }
    
    @Test
    public void testDecodeEmptyList() {
        assertEquals(LempelZivWelch.decode(new ArrayList()), "");
    }
    
    @Test //poikkeustapaus, esitetään https://www.cs.duke.edu/csed/curious/compression/lzw.html
    public void testDecodeNoDictionaryEntryFound() {
        String unencodedTest = "abababab";
        String decodedTest = LempelZivWelch.decode(LempelZivWelch.encode(unencodedTest));
        assertEquals(decodedTest, unencodedTest);
    }
    
}
