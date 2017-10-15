
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
        checkLatestByte();
        b = (byte) (b | i << --space);
        checkSpace();
//        if (i == 0) writeZeroBit();
//        else if (i == 1) writeOneBit();
    }
    
//    public void writeZeroBit() {
//        checkLatestByte();
//        space--;
//        checkSpace();
//    }
//    
//    public void writeOneBit() {
//        checkLatestByte();
//        b = (byte) (b | 0x01 << --space);
//        checkSpace();
//    }

    public void writeCharacter(char c) {
        checkLatestByte();
        b = (byte)(b | (c >>> 8 - space));
        bs.write(b);
        
        if (space < 8) {
            b = (byte)(c << space);
        } else {
            b = 0x00;
        }
    }

    public byte[] toByteArray(boolean writeEOF) {
        bs.write(b);
        if (writeEOF) bs.write((byte)(8 - space));    //EOF, kuinka monta bittiä luetaan "viimeisestä" tavusta
        return bs.toByteArray();
    }

    private void checkLatestByte() {
        if (space == 8) {
            b = 0x00;
        }
    }
    
    private void checkSpace() {
        if (space == 0) {
            bs.write(b);
            space = 8;
        }
    }
}