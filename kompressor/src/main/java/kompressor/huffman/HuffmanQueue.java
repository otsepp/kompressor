
package kompressor.huffman;

public class HuffmanQueue {
    private HuffmanNode[] a;
    private int heapSize;
    
    public HuffmanQueue() {
        a = new HuffmanNode[256];
        heapSize = 0;
    }
    
    public int size() {
        return heapSize;
    }
    
    public void add(HuffmanNode n) {
       heapSize++;
       int i = heapSize - 1;
       HuffmanNode par = a[parent(i)];
       while (i > 0 && par.getFrequency() > n.getFrequency()) {
           a[i] = par;
           i = parent(i);
           par = a[parent(i)];
       }
       a[i] = n;
   }
   
   public HuffmanNode poll() {
       if (heapSize == 0)  return null;
       HuffmanNode max = a[0];
       a[0] = a[heapSize - 1];
       a[(heapSize--) - 1] = null;
       heapify(0);
       return max;
   }
   
   private void heapify(int i) {
       int l = left(i);
       int r = right(i);
       if (r < heapSize) {
           int smallest;
           if (a[l].getFrequency() < a[r].getFrequency()) smallest = l;
           else smallest  = r;
           
           if (a[i].getFrequency() > a[smallest].getFrequency()) {
               swap(i, smallest);
               heapify(smallest);
           }
       } else if (l == heapSize - 1 && a[i].getFrequency() > a[l].getFrequency()) {
           swap(i, l);
       }
   }
   
   private void swap(int i, int j) {
       HuffmanNode na = a[i];
       a[i] = a[j];
       a[j] = na;
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
}
