package kompressor.lzw.structures;

public class lzwLinkedList<K, V> {
        private Node<K, V> top;
        
        public lzwLinkedList() {
        }
        public lzwLinkedList(K k, V v) {
            this.add(k, v);
        }
        
        //Tällä hetkellä listassa voi olla solmuja, joilla on sama avain, mutta eri arvo. 
        //LZW ei kuitenkaan lisää samoja avaimia useasti, joten tätä ei huomioida
        public final void add(K k, V v) {
            Node n = new Node(k, v);
            n.next = this.top;
            this.top = n;
        }
        
        //Etsii listasta sellaisen solmun arvon, jonka avain on k
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
        
        protected class Node<K, V> {
            private K k;
            private V v;
            private Node<K, V> next;
            protected Node(K k, V v) {
                this.k = k;
                this.v = v;
            }
        }
    }