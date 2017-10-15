package kompressor.huffman;

import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanTree;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Huffman {
    private Huffman() {
    }
    
    public static byte[] encode(byte[] bytes) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        HuffmanTree t = TreeBuilder.createTreeFromUnencodedBytes(bytes);

        bs.write(createHeader(t.getRoot(), new ByteArrayWriter()).toByteArray(false));
        
        ByteArrayWriter bwr = new ByteArrayWriter();
        for (byte b : bytes) {
            for (char c : t.searchCode((char) b).toCharArray()) {
                if (c == '0') {
                    bwr.writeZeroBit();
                } else {
                    bwr.writeOneBit();
                }
            }
        }
        bs.write(bwr.toByteArray(true));
        return bs.toByteArray();
    }
    
     public static byte[] decode(byte[] bytes) {
        TreeBuilderReturnObject tbr = TreeBuilder.createTreeFromEncodedBytes(bytes);
 
//        HuffmanTree t = tbr.getTree();
//        byte[] encoded = tbr.getLeftoverBytes();
        
        return null;
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
            bwr.writeOneBit();
            bwr.writeCharacter(n.getCharacter());
            return bwr;
        }
        bwr.writeZeroBit();
        bwr = createHeader(n.getLeft(), bwr);
        bwr = createHeader(n.getRight(), bwr);
        return bwr;
    }
     
}
