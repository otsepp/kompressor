package kompressor.lzw.structures;

public class lzwLinkedList<K, V> {
        public Node<K, V> top;
        
        public lzwLinkedList() {
        }
        public lzwLinkedList(K k, V v) {
            this.add(k, v);
        }
        
        public final void add(K k, V v) {
            Node n = new Node(k, v);
            n.next = this.top;
            if (n.next != null) {
                n.next.prev = n;
            }
            this.top = n;
        }
        
        public V search(K k) {
            Node n = this.top;
            while (n != null) { 
                if (n.k.equals(k)) {
                    return (V) n.v;
                }
                n = n.next;
            }
            return null;
        }
        
        @Override
        public String toString() {
            Node n = this.top;
            String r = "";
            while (n != null) {
                r = r + n.toString();
                if (n.next != null) {
                    r = r + "\n";
                }
                n = n.next;
            }
            return r;
        }
        
        public class Node<K, V> {
            public K k;
            public V v;
            public Node<K, V> next;
            public Node<K, V> prev;
            public Node(K k, V v) {
                this.k = k;
                this.v = v;
            }
            @Override
            public String toString() {
                return "Key: " + k + ", value: " + v;
            }
        }
    }