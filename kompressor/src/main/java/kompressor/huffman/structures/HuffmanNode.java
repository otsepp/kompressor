package kompressor.huffman.structures;

public class HuffmanNode {
    private Character c;
    private int freq;
    private HuffmanNode parent;
    private HuffmanNode right;
    private HuffmanNode left;
    private int id;
    
    private static int num = 0;
    
    public HuffmanNode(Character c, int freq) {
        this.c = c;
        this.freq = freq;
        id = ++num;
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
    
    public HuffmanNode getParent() {
        return this.parent;
    }
    
    public HuffmanNode getLeft() {
        return this.left;
    }
    
    public HuffmanNode getRight() {
        return this.right;
    }
    
    public void setParent(HuffmanNode n) {
        this.parent = n;
    }
    
    public void setLeft(HuffmanNode n) {
        this.left = n;
    }
    
    public void setRight(HuffmanNode n) {
        this.right = n;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof HuffmanNode) {
            HuffmanNode n = (HuffmanNode) o;
            if (this.id == n.id) return true;
        }
        return false;
    }
    
}