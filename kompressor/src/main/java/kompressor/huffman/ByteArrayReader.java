
package kompressor.huffman;

import java.io.ByteArrayOutputStream;

public class ByteArrayReader {
    private byte[] bytes;
    private int index;
    private int readBits;
    private int eofIndex;
    private final int ERROR_INT = 0xFF;
    
    public ByteArrayReader(byte[] bytes) {
        this.bytes = bytes;
        index = 0;
        readBits = 0;
        eofIndex = 8;
    }
    
     public ByteArrayReader(byte[] bytes, int eofIndex) {
        this.bytes = bytes;
        index = 0;
        readBits = 0;
        this.eofIndex = eofIndex;
    }
    
    public int readBit() {
        if (index == bytes.length) return ERROR_INT;
        if (index == this.bytes.length - 2 && readBits == eofIndex) return ERROR_INT;
        
        int b = ((bytes[index] & 0x80) >>> 7);
        
        if (++readBits == 8) {
            readBits = 0;
            index++;
        } else {
            bytes[index] = (byte) (bytes[index] << 1);
        }
        return b;
    }
  
    public char readCharacter() {
        byte c = 0x00;
        for (int i = 0; i < 8; i++) {
          c = (byte) (c << 1);
          c = (byte) (c | readBit());
        }
        return (char) c;
    }
  
    public byte[] getLeftoverBytes() {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        for (int i = (index + 1); i < bytes.length; i++) {
            bs.write(bytes[i]);
        }
        return bs.toByteArray();
    }
    
    public int getErrorInt() {
        return this.ERROR_INT;
    }
    
}
