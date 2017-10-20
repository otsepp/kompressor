package kompressor.huffman.structures;

public class IntQueue {
    //koko alussa
    private final int INIT_SIZE = 10;
    private int[] a;
    //taulukon koko, kaksinkertaistetaan kun tulee täyteen
    private int size;
     //indeksi, johon seuraava alkio lisätään
    private int index;
   
    
    public IntQueue() {
        a = new int[INIT_SIZE];
        size = INIT_SIZE;
        index = 0;
    }
    
    public void push(int i) {
        a[index++] = i;
        if (index == size) {
            int[] aNew = new int[size * 2];
            System.arraycopy(a, 0, aNew, 0, a.length);
            a = aNew;
        }
    }
    
    public Integer pop() {
        if (index == 0) return null;
        return a[--index];
    }
    
}
