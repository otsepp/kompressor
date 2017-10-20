package kompressor.huffman.bytearray;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ByteArrayReaderTest {
    
    public ByteArrayReaderTest() {
    }
    
    @Test
    public void testReadBit() {
        ByteArrayReader br = new ByteArrayReader(new byte[]{(byte) 0xFF, (byte) 0xFE});
        for (int i = 0; i < 15; i++) {
            assertEquals(1, br.readBit());
        }
        assertEquals(0, br.readBit());
        assertEquals(br.getErrorInt(), br.readBit());
    }

    @Test
    public void testReadCharacter() {
        ByteArrayReader br = new ByteArrayReader(new byte[]{97, 98});
        assertEquals('a', br.readCharacter());
        assertEquals('b', br.readCharacter());
    }

    @Test
    public void testGetLeftoverBytes() {
        ByteArrayReader br = new ByteArrayReader(new byte[]{0x00, 0x01});
        assertEquals(0x01, br.getLeftoverBytes()[0]);
    }

    @Test
    public void testEofIndex() {
        ByteArrayReader br = new ByteArrayReader(new byte[]{0x00, 0x00}, 2);
        assertEquals(0, br.readBit());
        assertEquals(0, br.readBit());
        assertEquals(br.getErrorInt(), br.readBit());
    }
    
}
