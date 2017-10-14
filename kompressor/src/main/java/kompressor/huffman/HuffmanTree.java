
package kompressor.huffman;

public class HuffmanTree {
    
    private HuffmanNode root;
    
    public HuffmanTree(HuffmanNode root) {
        this.root = root;
    }
    
    public HuffmanNode getRoot() {
        return this.root;
    }
    
    public String searchCode(char c) {
         if (root.getCharacter() != null && root.getCharacter() == c) {
             return "0";
         }
         return searchNode(root, c, new StringBuilder());
    }
    
     private String searchNode(HuffmanNode n, char c, StringBuilder code) {
        if (n.getCharacter() == null) {
            String left = searchNode(n.getLeft(), c, new StringBuilder(code).append("1"));
            String right = searchNode(n.getRight(), c, new StringBuilder(code).append("0"));
            if (left != null) return left;
            return right;
            
        } else {
            if (n.getCharacter() == c) {
                return code.toString();
            } else {
                return null;
            }
        }
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
    
     
}
