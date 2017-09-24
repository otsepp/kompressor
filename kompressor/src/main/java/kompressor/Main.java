
package kompressor;

import kompressor.huffman.Huffman;
import kompressor.huffman.HuffmanTree;



public class Main {

    public static void main(String[] args) {
        
        Huffman hf = new Huffman();
        
        HuffmanTree t = hf.tree;
        
//        System.out.println(t);
        
        System.out.println(t.searchCharacter("11110"));
        
    }
}
