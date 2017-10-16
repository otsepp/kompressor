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
            assertEquals(br.readBit(), 1);
        }
        assertEquals(br.readBit(), 0);
        assertEquals(br.readBit(), br.getErrorInt());
    }

    @Test
    public void testReadCharacter() {
        ByteArrayReader br = new ByteArrayReader(new byte[]{97, 98});
        assertEquals(br.readCharacter(), 'a');
        assertEquals(br.readCharacter(), 'b');
    }

    @Test
    public void testGetLeftoverBytes() {
        ByteArrayReader br = new ByteArrayReader(new byte[]{0x00, 0x01});
        assertEquals(br.getLeftoverBytes()[0], 0x01);
    }

    @Test
    public void testEofIndex() {
        ByteArrayReader br = new ByteArrayReader(new byte[]{0x00, 0x00}, 2);
        assertEquals(br.readBit(), 0);
        assertEquals(br.readBit(), 0);
        assertEquals(br.readBit(), br.getErrorInt());
    }
    
}
