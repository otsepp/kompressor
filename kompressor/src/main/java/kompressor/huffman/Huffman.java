package kompressor.huffman;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Huffman {
    private Huffman() {
    }
    
    public static byte[] encode(byte[] bytes) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        HuffmanTree t = buildTree(bytes);
//        ByteArrayWriter bwr = createHeader(t.getRoot(), new ByteArrayWriter());
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
        return bwr.toByteArray();
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
    
     public static byte[] decode(byte[] b) {
        
        return null;
    }
    
    private static HuffmanTree buildTree(byte[] bytes) {
        int[] freqs = new int[256];
        
        //talletetaan merkkien esiintymismäärät
        for (byte b : bytes) {
            freqs[b]++;
        }
        
        HuffmanQueue q = new HuffmanQueue();
        //laitetaan jonoon kaikki esiintyneet solmut
        for (int i = 0; i < freqs.length; i++) {   
            if (freqs[i] > 0) {
                q.add(new HuffmanNode((char) i, freqs[i])); 
            }
        }
        
        HuffmanTree t = null;
        while (q.size() > 0) {
            //jonosta poistetaan solmut (lehdet), joiden kirjaimia esiintyy vähiten
            HuffmanNode n0 = q.poll(); 
            HuffmanNode n1 = q.poll();  

            if (n1 != null) {
                //kun löytyy kaksi solmua, ne yhdistetään uudeksi
                HuffmanNode nu = new HuffmanNode(null, n0.getFrequency() + n1.getFrequency());
                nu.setRight(n0);
                nu.setLeft(n1);
                q.add(nu);  
            } else {
                //viimeinen solmu loydetään, asetetaan puun juureksi
                t = new HuffmanTree(n0);
            }
        }
        return t;
    }
    
}
