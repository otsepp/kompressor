package kompressor.huffman.structures;

public class IntQueue {
    private final int INIT_SIZE = 10;
    private int[] a;
    private int index;
    private int size;
    
    public IntQueue() {
        a = new int[INIT_SIZE];
        index = 0;
        size = INIT_SIZE;
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
