package kompressor.lzw;

import java.io.IOException;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class LempelZivWelchTest {
    
    private byte[] unencoded;
    private byte[] encoded;
    
    public LempelZivWelchTest() {
    }
    
    @Before
    public void setUp() {
        this.unencoded = new byte[] { 0x21, 0x20, 0x21, 0x20, 0x2B };   //"! ! +", [33,32, 256,43]
        this.encoded = new byte[] { 
            0x0, (byte) 0x21,   //12 bit code for 33
            0x0, (byte) 0x20,   
            0x1, 0x0,               //12 bit code for 256 (new dictionary entry)
            0x0, (byte) 0x2B
        };
    }
    
    @Test
     public void testEncode() throws IOException { 
         byte[] encodedTest = LempelZivWelch.encode(this.unencoded);
         assertEquals(true, Arrays.equals(this.encoded, encodedTest));
     }
     
    @Test
    public void testDecode() {
        byte[] decoded = LempelZivWelch.decode(this.encoded);
        assertEquals(true, Arrays.equals(this.unencoded, decoded));
    }
    
    @Test 
    public void testDecodeNoDictionaryEntryFound() throws IOException {
        byte[] encodedTest = new byte[] { 0x61, 0x62, 0x61, 0x62, 0x61, 0x62, 0x61 };
        byte[] decodedTest = LempelZivWelch.decode(LempelZivWelch.encode(encodedTest));
        assertEquals(true, Arrays.equals(encodedTest, decodedTest));
    }
    
}
