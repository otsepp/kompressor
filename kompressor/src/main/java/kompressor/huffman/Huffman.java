package kompressor.huffman;

import kompressor.huffman.bytearray.ByteArrayWriter;
import kompressor.huffman.bytearray.ByteArrayReader;
import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanTree;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import kompressor.huffman.structures.IntQueue;

public class Huffman {
    private Huffman() {
    }
    
    public static byte[] encode(byte[] bytes) throws IOException {
        if (bytes.length == 0) return bytes;
        
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        
        HuffmanTree t = TreeBuilder.createTreeFromUnencodedBytes(bytes);
        bs.write(createHeader(t.getRoot(), new ByteArrayWriter()).toByteArray(false));
        
        ByteArrayWriter bwr = new ByteArrayWriter();
        
        for (byte b : bytes) {
            IntQueue code = t.searchCode((char) Byte.toUnsignedInt(b));
            Integer bit;
            while ((bit = code.pop()) != null) bwr.writeBit(bit);
        }
        
        bs.write(bwr.toByteArray(true));
        return bs.toByteArray();
    }
    
     public static byte[] decode(byte[] bytes) {
         if (bytes.length == 0) return bytes;
         
        TreeBuilderReturnObject tbr = TreeBuilder.createTreeFromHeader(bytes);
        HuffmanTree t = tbr.getTree();
        byte[] encoded = tbr.getLeftoverBytes();
        
        //annetaan konstruktorissa viimeisessä tavussa sijaitseva eof-indeksi
        ByteArrayReader br = new ByteArrayReader(encoded, encoded[encoded.length - 1]);
        ByteArrayWriter bwr = new ByteArrayWriter();
        
        Integer b;
        HuffmanNode n = t.getRoot();
        
        while ((b = br.readBit()) != null) {
            //jos puussa on vain juuri
            if (n.getCharacter() != null) { 
                bwr.writeCharacter(n.getCharacter());
                continue;
            }
            
            if (b == 1) n = n.getLeft();
            if (b == 0) n = n.getRight();
            if (n.getCharacter() != null) {
                bwr.writeCharacter(n.getCharacter());
                n = t.getRoot();
            }
        }
        return bwr.toByteArray(false);
    }
     
     //Kirjoittaa headerin pakatun datan alkuun, josta rakennetaan puu purkaessa
     private static ByteArrayWriter createHeader(HuffmanNode n, ByteArrayWriter bwr) {
        if (n.getCharacter() != null) {
            bwr.writeBit(1);
            bwr.writeCharacter(n.getCharacter());
            return bwr;
        }
        bwr.writeBit(0);
        bwr = createHeader(n.getLeft(), bwr);
        bwr = createHeader(n.getRight(), bwr);
        return bwr;
    }
     
}
