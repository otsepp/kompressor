package kompressor.lzw.structures;

public class lzwDictionary<K, V> {
    lzwLinkedList<K, V>[] arr;
    int n = 4096;   
    int m = 1381; 
    int size;
    
    public lzwDictionary() {
        arr = new lzwLinkedList[m];
        size = 0;
    }

    public void put(K k, V v) {
        int i = hashFunction(k);
        if (this.arr[i] == null) {
            this.arr[i] = new lzwLinkedList(k, v);
        } else {
            this.arr[i].add(k, v);
        }
        size++;
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
    
    public int hashFunction(K k) {
        int code = k.hashCode() % m;
        if (code < 0) {
            return code*(-1);
        }
        return code;
    }   
}