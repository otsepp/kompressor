
package kompressor.huffman.structures;

//implements Iterable

import java.util.Iterator;

public class IntList implements Iterable<Integer> {
    private final int INIT_SIZE = 5;
    private int[] a;
    private int index;
    private int size;
    
    public IntList() {
        a = new int[INIT_SIZE];
        index = 0;
        size = INIT_SIZE;
    }
    
    public IntList(IntList c) {
        a = new int[c.size];
        index = c.index;
        size = c.size;
        System.arraycopy(c.a, 0, a, 0, a.length);
    }
    
    public Integer get(int i) {
        if (i < index && i > -1) {
            return a[i];
        }
        return null;
    }
    
    public void add(int i) {
        a[index++] = i;
        if (index == size) {
            extendList();
        }
    }
    
    private void extendList() {
        size *= 2;
        int[] aNew = new int[size];
        System.arraycopy(a, 0, aNew, 0, a.length);
        a = aNew;
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
        
        //ei tarvitse
        @Override
        public void remove() {
        }
        
    }
    
}
