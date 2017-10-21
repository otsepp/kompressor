
package kompressor.huffman.bytearray;

import java.io.ByteArrayOutputStream;

public class ByteArrayReader {
    private byte[] bytes;
    //tällä hetkellä luettavan tavun indeksi
    private int index;
    //luetut bitit
    private int readBits;
    //määrittää toiseksi viimeisestä tavusta luettavien bittien lukumäärän
    private int eofIndex;
    
    public ByteArrayReader(byte[] bytes) {
        this.bytes = bytes;
        index = 0;
        readBits = 0;
        eofIndex = 9;  //Jotta readBit() ei estä headerin lukemista
    }
    
    public ByteArrayReader(byte[] bytes, int eofIndex) {
        this.bytes = bytes;
        index = 0;
        readBits = 0;
        this.eofIndex = eofIndex;
    }
    
    //luetaan bittejä vasemmalta 
    public Integer readBit() {
        //kun yritetään lukea viimeisen pakatun koodin jälkeen
        if (index == bytes.length - 2 && readBits == eofIndex) return null;   
        
        //jos koko tavu on luettu, siirrytään seuraavaan
        if (readBits == 8) {
            if (++index == bytes.length) return null;
            readBits = 0;
        }
        
        int b = ((bytes[index] & 0x80) >>> 7);  //nykyisen tavun ensimmäinen bitti vasemmalta
        bytes[index] = (byte) (bytes[index] << 1);  //siirretään yhden vasemmalle
        readBits++;
        
        return b;
    }
  
    //luetaan 8 seuraavaa bittiä
    public char readCharacter() {
        byte c = 0x00;
        for (int i = 0; i < 8; i++) {
          c = (byte) (c << 1);
          c = (byte) (c | readBit());
        }
        return (char) c;
    }
  
    //käytetään, kun header on luettu ja palautetaan loput bitit (pakattu data ja eof-tavu)
    public byte[] getLeftoverBytes() {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        for (int i = (index + 1); i < bytes.length; i++) {
            bs.write(bytes[i]);
        }
        return bs.toByteArray();
    }
    
}
