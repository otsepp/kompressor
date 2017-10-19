package kompressor.huffman.bytearray;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class ByteArrayWriterTest {
    private ByteArrayWriter bwr;
    
    public ByteArrayWriterTest() {
    }

    @Before
    public void setUp() {
        bwr = new ByteArrayWriter();
    } 
    
    @Test
    public void testWriteBit() {
        for (int i = 0; i < 9; i++) {
            bwr.writeBit(1);
        }
        byte[] b = bwr.toByteArray(false);
        assertEquals((byte) 0xFF, b[0]);
        assertEquals((byte) 0x80, b[1]);
    }

    @Test
    public void testWriteCharacter() {
        bwr.writeCharacter('a');
        bwr.writeBit(0);
        bwr.writeCharacter('c');
        byte[] b = bwr.toByteArray(false);
        assertEquals((int) 'a', b[0]);
        assertEquals(((int) 'c') >>> 1, b[1]);
        assertEquals((byte) 0x80, b[2]);
    }  

    @Test
    public void testToByteArrayOneByte() {
        bwr.writeBit(1);
        byte[] b = bwr.toByteArray(false);
        assertEquals(1, b.length);
    }
    @Test
    public void testToByteArrayMultipleBytes() {
        bwr.writeBit(0);
        bwr.writeCharacter('a');
        byte[] b = bwr.toByteArray(false);
        assertEquals(2, b.length);
    }
    
    @Test
    public void testToByteArrayEof() {
        bwr.writeBit(0);
        bwr.writeBit(0);
        bwr.writeBit(0);
        bwr.writeBit(1);
        byte[] b = bwr.toByteArray(true);
        assertEquals(2, b.length);
        assertEquals((byte) 0x10, b[0]);
        assertEquals(4, b[1]);
    }
}
