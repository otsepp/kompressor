package kompressor.huffman;

import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanTree;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Huffman {
    private Huffman() {
    }
    
    public static byte[] encode(byte[] bytes) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        HuffmanTree t = TreeBuilder.createTreeFromUnencodedBytes(bytes);
        
        bs.write(createHeader(t.getRoot(), new ByteArrayWriter()).toByteArray(false));
        
        ByteArrayWriter bwr = new ByteArrayWriter();
        
        for (byte b : bytes) {
            for (int i : t.searchCode((char) b)) {
                bwr.writeBit(i);
            }
        }
        bs.write(bwr.toByteArray(true));
        return bs.toByteArray();
    }
    
     public static byte[] decode(byte[] bytes) {
        TreeBuilderReturnObject tbr = TreeBuilder.createTreeFromHeader(bytes);
        HuffmanTree t = tbr.getTree();
        byte[] encoded = tbr.getLeftoverBytes();
        
//        for (byte b : encoded) {
//            System.out.println(Integer.toBinaryString(Byte.toUnsignedInt(b)));
//        }
        
        ByteArrayReader br = new ByteArrayReader(encoded, encoded[encoded.length - 1]);
        ByteArrayWriter bwr = new ByteArrayWriter();
        List<Integer> code = new ArrayList();
        
        int b;
        while ((b = br.readBit()) != br.getErrorInt()) {
            code.add(b);
            Character cFound = t.searchCharacter(code);
            
            if (cFound != null) {
                bwr.writeCharacter(cFound);
                code = new ArrayList();
            }
        }
       
        
        return bwr.toByteArray(false);
//         StringBuilder sDecoded = new StringBuilder();
//         StringBuilder code = new StringBuilder();
//
//         for (char c : this.sEncoded.toCharArray()) {
//             code.append(c);
//             Character cFound = tree.searchCharacter(code.toString());
//
//             if (cFound != null) {
//                 sDecoded.append(cFound);
//                 code = new StringBuilder();
//             }
//         }
//         return sDecoded.toString();
    }
     
     private static ByteArrayWriter createHeader(HuffmanNode n, ByteArrayWriter bwr) throws IOException  {
        if (n.getCharacter() != null) {
            bwr.writeBit(1);
//            bwr.writeOneBit();
            bwr.writeCharacter(n.getCharacter());
            return bwr;
        }
        bwr.writeBit(0);
//        bwr.writeZeroBit();
        bwr = createHeader(n.getLeft(), bwr);
        bwr = createHeader(n.getRight(), bwr);
        return bwr;
    }
     
}
