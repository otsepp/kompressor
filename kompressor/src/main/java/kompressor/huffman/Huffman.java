package kompressor.huffman;

import kompressor.huffman.bytearray.ByteArrayWriter;
import kompressor.huffman.bytearray.ByteArrayReader;
import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanTree;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import kompressor.huffman.structures.IntList;

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
            char c = (char) Byte.toUnsignedInt(b);
            for (int i : t.searchCode(c)) {
                bwr.writeBit(i);
            }
        }
        bs.write(bwr.toByteArray(true));
        return bs.toByteArray();
    }
    
     public static byte[] decode(byte[] bytes) {
         if (bytes.length == 0) return bytes;
         
        TreeBuilderReturnObject tbr = TreeBuilder.createTreeFromHeader(bytes);
        HuffmanTree t = tbr.getTree();
        byte[] encoded = tbr.getLeftoverBytes();
        
        ByteArrayReader br = new ByteArrayReader(encoded, encoded[encoded.length - 1]);
        ByteArrayWriter bwr = new ByteArrayWriter();
        IntList code = new IntList();
        
        int b;
        while ((b = br.readBit()) != br.getErrorInt()) {
            code.add(b);
            Character cFound = t.searchCharacter(code);
            
            if (cFound != null) {
                bwr.writeCharacter(cFound);
                code = new IntList();
            }
        }
        return bwr.toByteArray(false);
    }
     
     public static ByteArrayWriter createHeader(HuffmanNode n, ByteArrayWriter bwr) throws IOException  {
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
