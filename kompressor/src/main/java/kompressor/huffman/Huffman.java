package kompressor.huffman;

import java.util.PriorityQueue;

public class Huffman {
//    private 
    public HuffmanTree tree;
    
    public Huffman() {
        //Testi
        this.buildTree("ajan aina autoa");
    }
    
    public String encode(String s) {
        return "";
    }
    
    private void buildTree(String s) {
        int[] freqs = new int[256];
        for (char c : s.toCharArray()) {
            freqs[c]++;
        }
        PriorityQueue<HuffmanNode> q = new PriorityQueue();
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > 0) {
                q.add(new HuffmanNode((char) i, freqs[i]));
            }
        }
        while (q.size() > 0) {
            HuffmanNode n0 = q.poll();
            HuffmanNode n1 = q.poll();
            if (n1 != null) {
                HuffmanNode nu = new HuffmanNode(null, n0.getFrequency() + n1.getFrequency());
                nu.setRight(n0);
                nu.setLeft(n1);
                q.add(nu);
            } else {
                this.tree = new HuffmanTree(n0);
            }
        }
    }
    
}
