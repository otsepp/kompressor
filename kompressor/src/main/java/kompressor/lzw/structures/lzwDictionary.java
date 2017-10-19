package kompressor.lzw.structures;

public class lzwDictionary<K, V> {
    private final int M = 5471; //Talletetaan max. 4096 avainta, tällä M saavutetaan täyttösuhde 0.75
    private lzwLinkedList<K, V>[] list;
    
    public lzwDictionary() {
        list = new lzwLinkedList[M];
    }
    
    //voidaan lisätä enemmän kuin 4096 paria, mutta tämän ei tulisi tapahtua
    public void put(K k, V v) { 
        int i = hashFunction(k);
        
        if (list[i] == null) {
            list[i] = new lzwLinkedList(k, v);
        } else {
            list[i].add(k, v);
        }
    }
    
    public V get(K k) {
        int i = hashFunction(k);
        if (list[i] != null) return list[i].search(k);
        return null;
    }
    
    public boolean containsKey(K k) {
        return this.get(k) != null; 
    }
    
    private int hashFunction(K k) {
        int code = k.hashCode() % M;
        if (code < 0) return code*(-1);
        return code;
    }   
}