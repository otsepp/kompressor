package kompressor.lzw.structures;

public class lzwDictionary<K, V> {
    private lzwLinkedList<K, V>[] arr;
    private final int N = 4096;   
    private final int M = 5471; 
    
    public lzwDictionary() {
        arr = new lzwLinkedList[M];
    }

    public void put(K k, V v) {
        int i = hashFunction(k);
        if (this.arr[i] == null) {
            this.arr[i] = new lzwLinkedList(k, v);
        } else {
            this.arr[i].add(k, v);
        }
    }
    public V get(K k) {
        int i = hashFunction(k);
        if (this.arr[i] != null) {
            return this.arr[i].search(k);
        }
        return null;
    }
    
    public boolean containsKey(K k) {
        return this.get(k) != null; 
    }
    
    private int hashFunction(K k) {
        int code = k.hashCode() % M;
        if (code < 0) {
            return code*(-1);
        }
        return code;
    }   
}