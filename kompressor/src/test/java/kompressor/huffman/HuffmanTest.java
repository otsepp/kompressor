package kompressor.huffman;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class HuffmanTest {
    private String unencoded;
    private String encoded;
    private Huffman h;
    
    public HuffmanTest() {
    }
    
    @Before
    public void setUp() {
        this.unencoded = "ajan aina autoa";
        this.encoded = "0101101001100101010001100111111111011100";
        this.h = new Huffman(this.unencoded);
    }
    
    @Test
    public void encodeTest() {
        assertEquals(this.encoded, this.h.encode());
    }
    
    @Test
    public void decodeTest() {
        this.h.encode();
        assertEquals(this.unencoded, this.h.decode());
    }
    
    @Test
    public void setStringTest() {
        this.h.encode();
        String s = "new letters!!!!";
        this.h.setString(s);
        assertEquals(s, this.h.getString());
        assertEquals("", this.h.getEncodedString());
        this.h.encode();
        assertEquals(s, this.h.decode());
    }
    
}
