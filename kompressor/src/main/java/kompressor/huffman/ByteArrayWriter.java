
package kompressor.huffman;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ByteArrayWriter {
    private ByteArrayOutputStream bs;
    private byte b;
    private int space;

    public ByteArrayWriter() {
        bs = new ByteArrayOutputStream();
        b = 0x00;
        space = 8;
    }
    
    public void writeBit(int i) {
        checkSpace();
        b = (byte) (b | i << --space);
    }

    public void writeCharacter(char c) {
        checkSpace();
        
        b = (byte)(b | (c >>> 8 - space));
        
        if (space < 8) {
            bs.write(b);
            b = (byte)(c << space);
        } else {
            space = 0;
        }
    }

    public byte[] toByteArray(boolean writeEOF) {
        bs.write(b);
        if (writeEOF) bs.write((byte)(8 - space));    //EOF, kuinka monta bittiä luetaan "viimeisestä" tavusta
        return bs.toByteArray();
    }

    private void checkSpace() {
        if (space == 0) {
            bs.write(b);
            space = 8;
            b = 0x00;
        }
    }
    
}