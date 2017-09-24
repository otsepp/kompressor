
package kompressor;

import kompressor.huffman.Huffman;

public class Main {

    public static void main(String[] args) {
        
        Huffman hf = new Huffman("ajan aina autoa");
        
        System.out.println(hf.encode());
        System.out.println(hf.decode());
        
    }
}
