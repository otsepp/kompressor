
package kompressor;

import java.util.PriorityQueue;

class Node implements Comparable<Node>{
    Character c;
    int freq;
    Node right;
    Node left;
    public Node(Character c, int freq) {
        this.c = c;
        this.freq = freq;
    }
    @Override
    public int compareTo(Node n) {
        if (n.freq > this.freq) {
            return -1;
        } else if (n.freq < this.freq) {
            return 1;
        }
        return 0;
    }
    @Override
    public String toString() {
        return c + ", freq: " + freq;
    }
}

class Tree {
    Node root;
    public Tree(Node root) {
        this.root = root;
    }
    
    public Character searchCharacter(String code) {
        Node n = root;
        if (n.c != null) {
            return n.c;
        }
        Character cr = null;
        for (char c : code.toCharArray()) {
            if (c == '1' && n.left != null) {
                n = n.left;
            } else if (c == '0' && n.right != null) {
                n = n.right;
            } else {
                return null;
            }
            cr = n.c;
        }
        return cr;
    }
    
     public String searchCode(char c) {
         return searchNode(root, c, new StringBuilder());
    }
    
     //CLEAN UP ??
     private String searchNode(Node n, char c, StringBuilder code) {
        if (n == null) {
            return null;
        }
        if (n.c == null) {
            String left = searchNode(n.left, c, new StringBuilder(code).append("1"));
            String right = searchNode(n.right, c, new StringBuilder(code).append("0"));
            
            if (left == null) {
                return right;
            } else {
                return left;
            }
            
        } else {
            if (n.c == c) {
                return code.toString();
            } else {
                return null;
            }
        }
     }
     
}

public class Main {

    public static void main(String[] args) {
        int[] freqs = new int[256];
//        Scanner s = new Scanner(System.in);
//        String input = s.nextLine();
        String input = "ajan aina autoa";
        
        for (char c : input.toCharArray()) {
            freqs[c]++;
        }
        
        PriorityQueue<Node> q = new PriorityQueue();
        
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > 0) {
//                System.out.println((char) i + "->" + freqs[i]);
                q.add(new Node((char) i, freqs[i]));
            }
        }
        Tree t = null;
        
        while (q.size() > 0) {
            Node n0 = q.poll();
            Node n1 = q.poll();
            
//            System.out.println(n0);
//            System.out.println(n1);
            
            if (n1 != null) {
                Node nu = new Node(null, n0.freq + n1.freq);
                nu.right = n0;
                nu.left = n1;
                
//                System.out.println(nu);
                
                q.add(nu);
            } else {
//                System.out.println(n0 + " (root)");
                t = new Tree(n0);
            }
        }
        System.out.println("\n***\n");
        
        System.out.println(t.searchCode('o'));
    }
}
