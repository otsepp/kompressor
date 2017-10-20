package kompressor.huffman.structures;

import java.util.Iterator;

public class IntList implements Iterable<Integer> {
    //koko alussa
    private final int INIT_SIZE = 10;
    private int[] a;
    //taulukon koko, kaksinkertaistetaan kun tulee täyteen
    private int size;
    //indeksi, johon seuraava alkio lisätään
    private int index;
    
    public IntList() {
        a = new int[INIT_SIZE];
        size = INIT_SIZE;
        index = 0;
    }
    
    public int length() {
        return index;
    }
    
    //luo uuden listan joka sisältää vanhat alkiot
    public IntList(IntList c) {
        a = new int[c.size];
        index = c.index;
        size = c.size;
        System.arraycopy(c.a, 0, a, 0, a.length);
    }
    
    public Integer get(int i) {
        if (i < index && i > -1) return a[i];
        return null;
    }
    
    public void add(int i) {
        a[index++] = i;
        if (index == size) {
            int[] aNew = new int[size * 2];
            System.arraycopy(a, 0, aNew, 0, a.length);
            a = aNew;
        }
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new IntListIterator(this);
    }
    
    public class IntListIterator implements Iterator<Integer> {
        private IntList list;
        private int index;
        
        public IntListIterator(IntList list) {
            this.list = list;
        }
        
        @Override
        public boolean hasNext() {
            if (list.get(index) != null) return true;
            return false;
        }
        
        @Override
        public Integer next() {
            return list.get(index++);
        }
        
        @Override
        public void remove() {
        }
        
    }
    
}
