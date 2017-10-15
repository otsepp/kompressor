package kompressor.huffman;

import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanQueue;
import kompressor.huffman.structures.HuffmanTree;

public class TreeBuilder {
    
    private TreeBuilder() {
    }
    
    public static TreeBuilderReturnObject createTreeFromEncodedBytes(byte[] bytes) {
        ByteArrayReader br = new ByteArrayReader(bytes);
        HuffmanTree t = null;
        
        if (br.readBits == 1) {
            
        } else if (br.readBits == 0) {
            
        }
        return null;
    }
    
    public static HuffmanTree createTreeFromUnencodedBytes(byte[] bytes) {
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
    
    public class TreeBuilderReturnObject {
        private HuffmanTree tree;
        private byte[] leftoverBytes;
        TreeBuilderReturnObject(HuffmanTree tree, byte[] leftoverBytes) {
            this.tree = tree;
            this.leftoverBytes = leftoverBytes;
        }
        public HuffmanTree getTree() {
            return this.tree;
        }
        public byte[] getLeftoverBytes() {
            return this.leftoverBytes;
        }
    }
    
}
