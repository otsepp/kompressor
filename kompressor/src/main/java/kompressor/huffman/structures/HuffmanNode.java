package kompressor.huffman.structures;

public class HuffmanNode {
    private Character c;
    private int freq;
    private HuffmanNode parent;
    private HuffmanNode right;
    private HuffmanNode left;
    private int id; //equals:ia varten, voidaan joutua verrata solmuja joiden c ja freq ovat samat
    
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
        return c;
    }
    
    public int getFrequency() {
        return freq;
    }
    
    public HuffmanNode getParent() {
        return parent;
    }
    
    public HuffmanNode getLeft() {
        return left;
    }
    
    public HuffmanNode getRight() {
        return right;
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
            if (id == n.id) return true;
        }
        return false;
    }
    
}