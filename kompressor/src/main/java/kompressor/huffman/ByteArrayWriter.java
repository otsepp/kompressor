
package kompressor.huffman;

import java.io.ByteArrayOutputStream;

public class ByteArrayWriter {
    ByteArrayOutputStream bs;
    byte b;
    int space;

    public ByteArrayWriter() {
        bs = new ByteArrayOutputStream();
        b = 0x00;
        space = 8;
    }
    
    public void writeZero() {
        space--;
        checkSpace();
    }
    
    public void writeOne() {
        b = (byte) (b | 0x01 << --space);
        checkSpace();
    }

    public void writeCharacter(char c) {
        b = (byte)(b | (c >>> 8 - space));
        bs.write(b);
        
        if (space < 8) {
            b = (byte)(c << space);
        } else {
            b = 0x00;
        }
    }

    public byte[] toByteArray() {
        if (b != 0x00) {
            bs.write(b);
        }
        return bs.toByteArray();
    }

    private void checkSpace() {
        if (space == 0) {
            bs.write(b);
            b = 0x00;
            space = 8;
        }
    }
}