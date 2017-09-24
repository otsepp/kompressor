package kompressor.huffman;

public class HuffmanNode implements Comparable<HuffmanNode>{
    private Character c;
    private int freq;
    private HuffmanNode right;
    private HuffmanNode left;
    
    public HuffmanNode(Character c, int freq) {
        this.c = c;
        this.freq = freq;
    }
    
    public Character getCharacter() {
        return this.c;
    }
    
    public int getFrequency() {
        return this.freq;
    }
    
    public HuffmanNode getLeft() {
        return this.left;
    }
    
    public HuffmanNode getRight() {
        return this.right;
    }
    
    public void setLeft(HuffmanNode n) {
        this.left = n;
    }
    
    public void setRight(HuffmanNode n) {
        this.right = n;
    }
    
    @Override
    public int compareTo(HuffmanNode n) {
        if (n.freq > this.freq) {
            return -1;
        } else if (n.freq < this.freq) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return this.c + ", freq: " + this.freq;
    }
    
}