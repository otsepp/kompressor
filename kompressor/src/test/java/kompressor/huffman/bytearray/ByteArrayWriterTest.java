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
        assertEquals(b[0], (byte) 0xFF);
        assertEquals(b[1], (byte) 0x80);
    }

    @Test
    public void testWriteCharacter() {
        bwr.writeCharacter('a');
        bwr.writeBit(0);
        bwr.writeCharacter('c');
        byte[] b = bwr.toByteArray(false);
        assertEquals(b[0], (int) 'a');
        assertEquals(b[1], ((int) 'c') >>> 1);
        assertEquals(b[2], (byte) 0x80);
    }  

    @Test
    public void testToByteArrayOneByte() {
        bwr.writeBit(1);
        byte[] b = bwr.toByteArray(false);
        assertEquals(b.length, 1);
    }
    @Test
    public void testToByteArrayMultipleBytes() {
        bwr.writeBit(0);
        bwr.writeCharacter('a');
        byte[] b = bwr.toByteArray(false);
        assertEquals(b.length, 2);
    }
    
    @Test
    public void testToByteArrayEof() {
        bwr.writeBit(0);
        bwr.writeBit(0);
        bwr.writeBit(0);
        bwr.writeBit(1);
        byte[] b = bwr.toByteArray(true);
        assertEquals(b.length, 2);
        assertEquals(b[0], (byte) 0x10);
        assertEquals(b[1], 4);
    }
}
