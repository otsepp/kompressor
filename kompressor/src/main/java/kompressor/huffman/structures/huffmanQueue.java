
package kompressor.huffman.structures;

import kompressor.huffman.HuffmanNode;

public class huffmanQueue {
    HuffmanNode[] arr;
    int arrLenght;
    int heapSize;
    
    public huffmanQueue() {
        
    }
    
   private int parent(int i) {
       return (int)((i - 1) / 2);
   }
   private int left(int i) {
       return 2*i + 1;
   }
   private int right(int i) {
       return 2*i + 2;
   }
    
   public void heapify(int i) {
       
   }
   
}
