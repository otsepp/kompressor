
package kompressor.huffman;

public class ByteArrayReader {
    byte[] bytes;
    int index;
    int readBits;
    
    public ByteArrayReader(byte[] bytes) {
        this.bytes = bytes;
        index = 0;
        readBits = 0;
    }
    
    public int readBit() {
        if (index == bytes.length) return 0xFF;
        
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
    
}
