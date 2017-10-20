
package kompressor.huffman.structures;

public class HuffmanTree {
    private HuffmanNode root;
    //käytetään vain koodien etsimiseen
    private HuffmanNode[] leafs;
    
    public HuffmanTree(HuffmanNode root) {
        this.root = root;
    }
    
    public HuffmanTree(HuffmanNode root, HuffmanNode[] leafs) {
        this.root = root;
        this.leafs = leafs;
    }
    
    public HuffmanNode getRoot() {
        return this.root;
    }
    
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

    //parempi toteuttaa pinolla jotta saatasiin koodit oikeassa järjestyksessä
    public IntQueue searchCode(char c) {
        HuffmanNode n = this.getLeaf(c);    //aloitetaan lehdestä, liikutaan juurta kohti
        
        IntQueue code = new IntQueue();
        
        HuffmanNode par = n.getParent();
        if (par != null) {
            while (par != null) {
                if (par.getLeft().equals(n)) code.push(1);
                if (par.getRight().equals(n)) code.push(0);
                n = par;
                par = par.getParent();
            }
            return code;

        } else { //kun puussa on vain yksi solmu, sitä aina vastaa koodi 0
            code.push(0);
            return code;
        }
        
//        IntList code = new IntList();
//        
//        HuffmanNode par = n.getParent();
//        if (par != null) {
//            while (par != null) {
//                if (par.getLeft().equals(n)) code.add(1);
//                if (par.getRight().equals(n)) code.add(0);
//                n = par;
//                par = par.getParent();
//            }
//            return code;
//
//        } else { //kun puussa on vain yksi solmu, sitä aina vastaa koodi 0
//            code.add(0);
//            return code;
//        }
    }
     
    private HuffmanNode getLeaf(char c) {
        return leafs[c];
    }
    
}
