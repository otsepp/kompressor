package kompressor.lzw;

import kompressor.lzw.LempelZivWelch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LempelZivWelchDemoTest {
    
    private String unencoded;
    private List<Integer> encoded;
    
    public LempelZivWelchDemoTest() {
    }

    @Before
    public void initialise() {
        this.unencoded = "TOBEORNOTTOBEORTOBEORNOT";
        this.encoded = Arrays.asList(84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263);
    }
    
    @Test
    public void testEncode() {
        List<Integer> encodedTest = LempelZivWelchDemo.encode(unencoded);
        assertEquals(encodedTest, encoded);
    }

    @Test
    public void testEncodeEmptyString() {
        assertEquals(LempelZivWelchDemo.encode(""), new ArrayList());
    }
    
    @Test
    public void testDecode() {
        String decodedTest = LempelZivWelchDemo.decode(encoded);
        assertEquals(decodedTest, unencoded);
    }
    
    @Test
    public void testDecodeEmptyList() {
        assertEquals(LempelZivWelchDemo.decode(new ArrayList()), "");
    }
    
    @Test //poikkeustapaus, esitetään https://www.cs.duke.edu/csed/curious/compression/lzw.html kohdassa Uncompression
    public void testDecodeNoDictionaryEntryFound() {
        String unencodedTest = "abababab";
        String decodedTest = LempelZivWelchDemo.decode(LempelZivWelchDemo.encode(unencodedTest));
        assertEquals(decodedTest, unencodedTest);
    }
    
}
