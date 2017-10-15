package kompressor.huffman.demo;

import kompressor.huffman.structures.HuffmanNode;

public class HuffmanTreeDemo {
    private HuffmanNode root;
    
    public HuffmanTreeDemo(HuffmanNode root) {
        this.root = root;
    }
    
    public HuffmanNode getRoot() {
        return this.root;
    }
    
    public Character searchCharacter(String code) {
        HuffmanNode n = root;
        if (n.getCharacter() != null) {
            return n.getCharacter();
        }
        Character cr = null;
        for (char c : code.toCharArray()) {
            if (c == '1' && n.getLeft() != null) {
                n = n.getLeft();
            } else if (c == '0' && n.getRight() != null) {
                n = n.getRight();
            } else {
                return null;
            }
            cr = n.getCharacter();
        }
        return cr;
    }
    
     public String searchCode(char c) {
         //jos vain 1 kirjain koko koodattavassa merkkijonossa, muuten root:n kirjain on null
         if (root.getCharacter() != null && root.getCharacter() == c) {
             return "0";
         }
         return searchNode(root, c, new StringBuilder());
    }
    
     //ehkä voi tehdä siistimmin
     private String searchNode(HuffmanNode n, char c, StringBuilder code) {
        if (n == null) {
            return null;
        }
        if (n.getCharacter() == null) {
            String left = searchNode(n.getLeft(), c, new StringBuilder(code).append("1"));
            String right = searchNode(n.getRight(), c, new StringBuilder(code).append("0"));
            
            if (left == null) {
                return right;
            } else {
                return left;
            }
        } else {
            if (n.getCharacter() == c) {
                return code.toString();
            } else {
                return null;
            }
        }
     }
     
}
