package kompressor.huffman.structures;

public class HuffmanNode {
    private Character c;
    private int freq;
    private HuffmanNode right;
    private HuffmanNode left;
    
    public HuffmanNode(Character c, int freq) {
        this.c = c;
        this.freq = freq;
    }
    
    public HuffmanNode(Character c) {
        this.c = c;
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
    public String toString() {
        return this.c + "";
    }
    
}