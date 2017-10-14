package kompressor.huffman;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Huffman {
    private Huffman() {
    }
    
    public static byte[] encode(byte[] b) throws IOException {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        HuffmanTree t = buildTree(b);
        ByteArrayWriter bwr = createHeader(t.getRoot(), new ByteArrayWriter());
        
//        StringBuilder code = new StringBuilder();
//        for (char c : this.s.toCharArray()) {
//            code.append(this.tree.searchCode(c));
//        }
//        this.sEncoded = code.toString();
//        return this.sEncoded;
        return null;
    }
    
    private static ByteArrayWriter createHeader(HuffmanNode n, ByteArrayWriter bwr) throws IOException  {
        if (n.getCharacter() != null) {
            bwr.writeOne();
            bwr.writeCharacter(n.getCharacter());
            return bwr;
        }
        bwr.writeZero();
        bwr = createHeader(n.getLeft(), bwr);
        bwr = createHeader(n.getRight(), bwr);
        return bwr;
    }
    
    private static HuffmanTree buildTree(byte[] b) {
        int[] freqs = new int[256];
        
        //talletetaan merkkien esiintymismäärät
        for (byte bL : b) {
            freqs[bL]++;
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
    
    
    public static byte[] decode(byte[] b) {
        
        return null;
    }
    
    
    
}
