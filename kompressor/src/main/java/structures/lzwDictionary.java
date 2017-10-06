package structures;

public class lzwDictionary<K, V> {
    lzwLinkedList<K, V>[] arr;
    int n = 4096;   //512, 1024, 2048, 4096
    int m = 4099;  //521, 1031, 2053, 4099
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
            return (V) this.arr[i].search(k).v;
        }
        return null;
    }
    
    public int hashFunction(K k) {
        return k.hashCode() % m;
    }   
}

