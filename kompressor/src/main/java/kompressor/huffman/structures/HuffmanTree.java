
package kompressor.huffman.structures;

public class HuffmanTree {
    
    private HuffmanNode root;
    
    public HuffmanTree(HuffmanNode root) {
        this.root = root;
    }
    
    public HuffmanNode getRoot() {
        return this.root;
    }
    
    //huonosti totetutettu, hyvin hidas
    public Character searchCharacter(IntList code) {
        HuffmanNode n = root;
        if (n.getCharacter() != null) return n.getCharacter();
        
        Character chr = null;
        
        for (int c : code) {
            if (c == 1 && n.getLeft() != null) n = n.getLeft();
            else if (c == 0 && n.getRight() != null) n = n.getRight();
            else return null;
            chr = n.getCharacter();
        }
        return chr;
    }
    
    public IntList searchCode(char c) {
        IntList code = new IntList();
        //kun puussa on vain yksi solmu, sitä aina vastaa koodi 0
        if (root.getCharacter() != null && root.getCharacter() == c) {
            code.add(0);
            return code;
        }
        return searchNode(root, c, code);
    }
    
    private IntList searchNode(HuffmanNode n, char c, IntList code) {
        if (n.getCharacter() == null) {
            //luodaan kopiot syötelistasta
            IntList left = new IntList(code);
            IntList right  = new IntList(code);
            
            left.add(1);
            right.add(0);
            
            left = searchNode(n.getLeft(), c, left);
            right = searchNode(n.getRight(), c, right);
            
            if (left != null) return left;
            return right;
        } else {   //polku, jonka kautta oikea merkki löydetään, on ainoa joka palauttaa ei-null -arvon
            if (n.getCharacter() == c) return code;
            return null;
        }
    }
     
}
