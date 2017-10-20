package kompressor.huffman;

import kompressor.huffman.bytearray.ByteArrayReader;
import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanQueue;
import kompressor.huffman.structures.HuffmanTree;

public class TreeBuilder {
    
    private TreeBuilder() {
    }
    
    public static HuffmanTree createTreeFromUnencodedBytes(byte[] bytes) {
        int[] freqs = new int[256];
        //talletetaan merkkien esiintymismäärät
        for (byte b : bytes) {
            freqs[Byte.toUnsignedInt(b)]++;
        }
        HuffmanQueue q = new HuffmanQueue();
        HuffmanNode[] leafs = new HuffmanNode[256];
        //laitetaan jonoon kaikki esiintyneet solmut
        for (int i = 0; i < freqs.length; i++) {   
            if (freqs[i] > 0) {
                HuffmanNode n = new HuffmanNode((char) i, freqs[i]);
                q.add(n);
                leafs[i] = n;
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
                n0.setParent(nu);
                
                nu.setLeft(n1);
                n1.setParent(nu);
                
                q.add(nu);  
            } else {
                //viimeinen solmu loydetään, asetetaan puun juureksi
                t = new HuffmanTree(n0, leafs);
            }
        }
        return t;
    }
    
    public static TreeBuilderReturnObject createTreeFromHeader(byte[] bytes) {
        ByteArrayReader br = new ByteArrayReader(bytes);
        HuffmanTree t = null;
        
        //kun puussa on vain yksi solmu
        if (br.readBit() == 1) {
            t = new HuffmanTree(new HuffmanNode(br.readCharacter()));
            return new TreeBuilderReturnObject(t, br.getLeftoverBytes());
        } 
        
        HuffmanNode root = buildNode(new HuffmanNode(null), br);
        t = new HuffmanTree(root);
        
        return new TreeBuilderReturnObject(t, br.getLeftoverBytes());
    }
    
   private static HuffmanNode buildNode(HuffmanNode n, ByteArrayReader br) {
       //vasen lapsi
        if (br.readBit() == 1) n.setLeft(new HuffmanNode(br.readCharacter()));
        else n.setLeft(buildNode(new HuffmanNode(null), br));
        
        //oikea lapsi
        if (br.readBit() == 1) n.setRight(new HuffmanNode(br.readCharacter()));
        else n.setRight(buildNode(new HuffmanNode(null), br));
        
        return n;
    }
    
}
