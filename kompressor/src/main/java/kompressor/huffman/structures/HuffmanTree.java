
package kompressor.huffman.structures;

import java.util.ArrayList;
import java.util.List;

public class HuffmanTree {
    
    private HuffmanNode root;
    
    public HuffmanTree(HuffmanNode root) {
        this.root = root;
    }
    
    public HuffmanNode getRoot() {
        return this.root;
    }
    
    public List<Integer> searchCode(char c) {
        List<Integer> code = new ArrayList();
         if (root.getCharacter() != null && root.getCharacter() == c) {
             code.add(0);
             return code;
         }
        return searchNode(root, c, code);
    }
    
     private List<Integer> searchNode(HuffmanNode n, char c, List<Integer> code) {
         if (n.getCharacter() == null) {
             List<Integer> left = new ArrayList(code);
             List<Integer> right = new ArrayList(code);
             
             left.add(1);
             right.add(0);
             
             left = searchNode(n.getLeft(), c, left);
             right = searchNode(n.getRight(), c, right);
             
             if (left != null) return left;
             return right;
             
         } else {
             if (n.getCharacter() == c) return code;
             return null;
         }
     }
     
      public Character searchCharacter(List<Integer> code) {
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
    
     
}
