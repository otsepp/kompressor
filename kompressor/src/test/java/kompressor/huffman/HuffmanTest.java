package kompressor.huffman;

import java.io.IOException;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class HuffmanTest {
    private final String unencoded = "cbboooiiiieeeee";
    private final byte[] encoded = new byte[] {
       0x2C, (byte) 0xB6, (byte) 0x92, (byte) 0xC5, 0x63, (byte) 0xB7, (byte) 0x80, //header
       0x4D, (byte) 0x81, 0x55, (byte) 0xFF, (byte) 0x80, //data
       0x1   //eof
    };
    
    public HuffmanTest() {
    }

    @Test
    public void testEncode() throws Exception {
        assertArrayEquals(encoded, Huffman.encode(unencoded.getBytes()));
    }

    @Test
    public void testEncodeEmpty() throws IOException {
        byte[] b = new byte[]{};
        assertArrayEquals(b, Huffman.encode(b));
    }
    
    @Test
    public void testDecode() {
        assertArrayEquals(unencoded.getBytes(), Huffman.decode(encoded));
    }
  
    @Test
    public void testDecodeEmpty() {
        byte[] b = new byte[]{};
        assertArrayEquals(b, Huffman.decode(b));
    }
    
    //Syötteitä joilla alg ei saata toimia vaikka se toimii yllä olevalla testisyötteellä
    
     @Test
    public void testSingleCharacter() throws IOException {  //puu jossa on vain juuri
        byte b[] = "aaaaa".getBytes();
        assertArrayEquals(b, Huffman.decode(Huffman.encode(b)));
    }
    
    @Test
    public void testAlternate1() throws IOException {   //EOF on 8
        byte[] b = "genesis".getBytes();
        assertArrayEquals(b, Huffman.decode(Huffman.encode(b)));
    }
    
    @Test
    public void testAlternate2() throws IOException {   //verrattavana kaksi null-solmua ilman frekvenssejä
        byte[] b = "apw93n".getBytes();
        assertArrayEquals(b, Huffman.decode(Huffman.encode(b)));
    }
    
}
