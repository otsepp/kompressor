
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
        //käydään koodi läpi, jos bitti on 1 siirrytään vasemmalle, muuten oikealle
        for (int c : code) {  
            if (c == 1) n = n.getLeft();  
            else if (c == 0) n = n.getRight();
            chr = n.getCharacter();     //lehti löydetty
        }
        return chr;
    }
    
    public IntQueue searchCode(char c) {
        HuffmanNode n = this.getLeaf(c);    //aloitetaan lehdestä, liikutaan juurta kohti
        IntQueue code = new IntQueue();
        HuffmanNode par = n.getParent();
        
        if (par != null) {
            while (par != null) {
                if (par.getLeft().equals(n)) code.push(1);  //jos edellinen solmu oli vasemmalla
                if (par.getRight().equals(n)) code.push(0); //jos oikealla
                n = par;
                par = par.getParent();
            }
            return code;

        } else { //kun puussa on vain yksi solmu, sitä aina vastaa koodi 0
            code.push(0);
            return code;
        }
    }
     
    private HuffmanNode getLeaf(char c) {
        return leafs[c];
    }
    
}
