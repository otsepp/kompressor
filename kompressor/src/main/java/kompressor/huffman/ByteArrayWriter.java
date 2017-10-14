
package kompressor.huffman;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayWriter {
    private ByteArrayOutputStream bs;
    private byte b;
    private int space;

    public ByteArrayWriter() {
        bs = new ByteArrayOutputStream();
        b = 0x00;
        space = 8;
    }
    
//    public void writeBit(int i) {
//        if (i == 0 || i == 1) {
//            b = (byte)(b | i << --space);
//            checkSpace();
//        }
//    }
    
    public void writeZeroBit() {
        space--;
        checkSpace();
    }
    
    public void writeOneBit() {
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