package kompressor.lzw;

import java.io.IOException;
import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
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
        this.unencoded = new byte[]{'!', ' ', '!', ' ', '+'};
        this.encoded = new byte[] { 
            0x0, '!',   //12 bit code for 33
            0x0, ' ',   
            0x1, 0x0,               //12 bit code for 256 (new dictionary entry)
            0x0, '+'
        };
    }
    
    @Test
     public void testEncode() throws IOException { 
         assertArrayEquals(this.encoded, LempelZivWelch.encode(this.unencoded));
     }
     
     @Test
     public void testEncodeEmpty() throws IOException {
         assertArrayEquals(new byte[]{}, LempelZivWelch.encode(new byte[]{}));
     }
     
    @Test
    public void testDecode() {
        assertArrayEquals(this.unencoded, LempelZivWelch.decode(this.encoded));
    }
    
    @Test
     public void testDecodeEmpty() {
         assertArrayEquals(new byte[]{}, LempelZivWelch.decode(new byte[]{}));
     }
    
    @Test 
    public void testDecodeNoDictionaryEntryFound() throws IOException {
        byte[] encodedTest = new byte[] { 0x61, 0x62, 0x61, 0x62, 0x61, 0x62, 0x61 };
        byte[] decodedTest = LempelZivWelch.decode(LempelZivWelch.encode(encodedTest));
        assertEquals(true, Arrays.equals(encodedTest, decodedTest));
    }
    
}
